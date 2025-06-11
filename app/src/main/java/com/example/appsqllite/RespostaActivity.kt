package com.example.appsqllite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.graphics.Color
import android.content.Intent
import com.example.appsqllite.R

class RespostaActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resposta)

        // Referências dos componentes
        val edtNome: EditText = findViewById(R.id.edtNome)
        val edtEndereco: EditText = findViewById(R.id.edtEndereco)
        val edtBairro: EditText = findViewById(R.id.edtBairro)
        val edtCep: EditText = findViewById(R.id.edtCep)
        val edtObservacao: EditText = findViewById(R.id.edtObservacao)
        val btnConfirmar: Button = findViewById(R.id.btnConfirmar)

        // Estilização dos EditTexts
        val editTexts = listOf(edtNome, edtEndereco, edtBairro, edtCep, edtObservacao)
        editTexts.forEach { editText ->
            editText.setBackgroundResource(R.drawable.edittext_background)
            editText.setTextColor(Color.BLACK)
            editText.setHintTextColor(Color.GRAY)
            editText.textSize = 16f
            editText.isEnabled = false // Deixar como somente leitura
        }

        // Estilo do botão
        btnConfirmar.apply {
            setBackgroundResource(R.drawable.button_background)
            setTextColor(Color.WHITE)
            textSize = 18f
            elevation = 8f
        }

        // Efeito de clique
        btnConfirmar.setOnTouchListener { v, event ->
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

        // Preencher os dados recebidos
        edtNome.setText(intent.getStringExtra("nome"))
        edtEndereco.setText(intent.getStringExtra("endereco"))
        edtBairro.setText(intent.getStringExtra("bairro"))
        edtCep.setText(intent.getStringExtra("cep"))
        edtObservacao.setText(intent.getStringExtra("observacao"))

        // Botão para seguir para a próxima tela
        btnConfirmar.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
