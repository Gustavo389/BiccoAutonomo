package com.bicco.autonomo.Form

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bicco.autonomo.Form.Login.Biccorequests
import com.bicco.autonomo.Form.Login.Biccorequests.login
import com.bicco.autonomo.Form.Registro.validarfields
import com.bicco.autonomo.HomeScreen.Identificacao
import com.bicco.autonomo.databinding.ActivityFormLoginBinding
import com.example.abalateral.MainBcc

class FormLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityFormLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var btt_login = binding.btEntrar
        var txt_register = binding.textTelaCadastro


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
                    println("$edt_email,$edt_senha")
                    if (Id != 0) {
                        Identificacao.setId(Id)
                        println("-----------")
                        println("Bem vindo")
                        var intent = Intent(this, MainBcc::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        println("algo de errado nao esta certo")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            thread.start()


        }


    }
}