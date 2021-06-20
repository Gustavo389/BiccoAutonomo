package com.bicco.autonomo.Form

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bicco.autonomo.databinding.ActivityFormLoginBinding
import com.example.abalateral.MainBcc

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
            var intent = Intent(this, MainBcc::class.java)
            startActivity(intent)
            finish()
        }



    }
}