package com.example.appsqllite

import android.content.Intent
import android.graphics.Color
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.appsqllite.db.DBHelper
import com.google.android.material.snackbar.Snackbar
import com.example.appsqllite.R.drawable.button_background
import com.example.appsqllite.R.drawable.edittext_background

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Referências dos componentes
        val edtNome: EditText = findViewById(R.id.edtNome)
        val edtEndereco: EditText = findViewById(R.id.edtEndereco)
        val edtBairro: EditText = findViewById(R.id.edtBairro)
        val edtCep: EditText = findViewById(R.id.edtCep)
        val edtObservacao: EditText = findViewById(R.id.edtObservacao)
        val btnCadastrar: Button = findViewById(R.id.btnCadastrar)

        // Configuração do CEP para aceitar apenas números
        edtCep.inputType = InputType.TYPE_CLASS_NUMBER

        // Estilo dos EditTexts
        val editTexts = listOf(edtNome, edtEndereco, edtBairro, edtCep, edtObservacao)
        editTexts.forEach { editText ->
            editText.setBackgroundResource(edittext_background)
            editText.setTextColor(Color.BLACK)
            editText.setHintTextColor(Color.GRAY)
            editText.textSize = 16f
        }

        // Estilo do botão
        btnCadastrar.apply {
            setBackgroundResource(button_background)
            setTextColor(Color.WHITE)
            textSize = 18f
            elevation = 8f
        }

        // Efeito de clique no botão
        btnCadastrar.setOnTouchListener { v, event ->
            when (event.action) {
                android.view.MotionEvent.ACTION_DOWN -> {
                    v.alpha = 0.7f
                    v.elevation = 4f
                }
                android.view.MotionEvent.ACTION_UP, android.view.MotionEvent.ACTION_CANCEL -> {
                    v.alpha = 1f
                    v.elevation = 8f
                }
            }
            false
        }

        // Ação do botão Cadastrar
        btnCadastrar.setOnClickListener {
            val nome = edtNome.text.toString().trim()
            val endereco = edtEndereco.text.toString().trim()
            val bairro = edtBairro.text.toString().trim()
            val cep = edtCep.text.toString().trim()
            val observacao = edtObservacao.text.toString().trim()

            if (nome.isNotEmpty() && endereco.isNotEmpty() && bairro.isNotEmpty() && cep.isNotEmpty()) {
                val dbHelper = DBHelper(this, null)

                // Gerar data atual no formato desejado
                val currentDate = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())

                dbHelper.addPessoa(nome, endereco, bairro, cep, currentDate, observacao)

                val intent = Intent(this, RespostaActivity::class.java).apply {
                    putExtra("nome", nome)
                    putExtra("endereco", endereco)
                    putExtra("bairro", bairro)
                    putExtra("cep", cep)
                    putExtra("observacao", observacao)
                }
                startActivity(intent)
            } else {
                Snackbar.make(it, "Preencha todos os campos obrigatórios!", Snackbar.LENGTH_LONG).show()
            }
        }}}
