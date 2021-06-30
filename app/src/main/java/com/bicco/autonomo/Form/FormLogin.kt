package com.bicco.autonomo.Form

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bicco.autonomo.Form.Login.Biccorequests
import com.bicco.autonomo.Form.Login.Biccorequests.login
import com.bicco.autonomo.Form.Registro.validarfields
import com.bicco.autonomo.HomeScreen.Identificacao
import com.bicco.autonomo.HomeScreen.TelaRecuperacao
import com.bicco.autonomo.databinding.ActivityFormLoginBinding
import com.bicco.autonomo.geradorDeSenha.gerador
import com.example.abalateral.MainBcc
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FormLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityFormLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var btt_login = binding.btEntrar
        var txt_register = binding.textTelaCadastro
        val txt_esqueci = binding.txtRecuperarsenhaLogin

        txt_register.setOnClickListener() {
            var intent = Intent(this, FormRegistrar::class.java)
            startActivity(intent)
            finish()
        }

        btt_login.setOnClickListener() {
            val thread = Thread {
                try {
                    var edt_email = binding.editEmail.text.toString()
                    var edt_senha = binding.editSenha.text.toString()

                    val Id = Biccorequests.login(
                        "https://bicco-api.herokuapp.com/login/autonomo",
                        "$edt_email",
                        "$edt_senha"

                    )
                    showToast("Validando Dados...")
                    println("$edt_email,$edt_senha")
                    if (Id != 0) {
                        Identificacao.setId(Id)
                        println("-----------")

                        showToast("Bem vindo")
                        var intent = Intent(this, MainBcc::class.java)
                        startActivity(intent)
                        finish()

                    } else {
                        showToast("Login incorreto")
                        val intent = Intent(this,FormLogin::class.java)
                        startActivity(intent)
                        finish()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            thread.start()


        }
            txt_esqueci.setOnClickListener(){


                        var intent = Intent(this, TelaRecuperacao::class.java)
                        startActivity(intent)
                        finish()
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
}}