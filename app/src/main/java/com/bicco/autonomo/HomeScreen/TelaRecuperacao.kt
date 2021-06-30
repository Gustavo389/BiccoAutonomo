package com.bicco.autonomo.HomeScreen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bicco.autonomo.Form.Login.Biccorequests
import com.bicco.autonomo.databinding.TelaRecuperacaoBinding
import com.example.abalateral.MainBcc
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class TelaRecuperacao : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = TelaRecuperacaoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val btton_enviar_senha = binding.enviarSenhaRecuperacao
        super.onCreate(savedInstanceState)
        btton_enviar_senha.setOnClickListener() {
            val txt_valor_senha = binding.edtBoxInserirRecuperacao.text.toString()
            showToast("Validando Dados...")
            if (txt_valor_senha != null) {
                val scope = CoroutineScope(Job())
                scope.launch {
                    val retornoRecuperacao = Biccorequests.recuperarSenha(
                        "https://bicco-api.herokuapp.com/senha/autonomo/recuperar",
                        txt_valor_senha)

                    if (retornoRecuperacao != 0) {
                        runOnUiThread(Runnable {
                            showToast("Chave de recuperação de senha correta")
                            showToast("Você utilizou a senha de segurança, caso se esqueceu de sua senha, altere-a imediatamente no perfil")
                            scope.launch {
                            Biccorequests.verAutonomo(
                                "https://bicco-api.herokuapp.com/ver/autonomo",
                                retornoRecuperacao
                            )
                            }
                            var intent = Intent(this@TelaRecuperacao, MainBcc::class.java)
                            startActivity(intent)
                            finish()
                        })
                    } else {
                        runOnUiThread(Runnable {
                            showToast("Chave de recuperação incorreta")
                        })
                    }
                }


            }


        }

    }

    fun showToast(texto: String) {
        runOnUiThread {
            Toast.makeText(
                this,
                texto,
                Toast.LENGTH_SHORT
            ).show()

        }
    }
}