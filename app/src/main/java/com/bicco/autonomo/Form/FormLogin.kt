package com.bicco.autonomo.Form

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bicco.autonomo.databinding.ActivityFormLoginBinding
import com.example.abalateral.MainBcc
import pacotebicco.fazerLogin

class FormLogin : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityFormLoginBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var btt_login = binding.btEntrar
        var txt_register = binding.textTelaCadastro
        var edt_email = binding.editEmail
        var edt_senha = binding.editSenha

        txt_register.setOnClickListener() {
            var intent = Intent(this, FormRegistrar::class.java)
            startActivity(intent)
            finish()
        }
        btt_login.setOnClickListener() {
            val Id = fazerLogin.login(
                "https://bicco-api.herokuapp.com/login/autonomo",
                "$edt_email",
                "$edt_senha"
            )
            if (Id != 0) {
                println("-----------")
                println("Bem vindo")
                var intent = Intent(this, MainBcc::class.java)
                startActivity(intent)
                finish()
            } else {
                println("algo de errado nao esta certo")
            }


        }


    }
}