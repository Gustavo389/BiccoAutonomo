package com.bicco.autonomo.Form

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bicco.autonomo.R
import com.bicco.autonomo.databinding.ActivityFormLoginBinding
import com.bicco.autonomo.databinding.ActivityFormRegistrarBinding

class FormRegistrar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val binding = ActivityFormRegistrarBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        var edt_register_email = binding.editEmailRegister
        var edt_register_senha = binding.editSenhaRegister
        var btt_register = binding.bttRegistrar
        var text_login = binding.textTelaLogin

        text_login.setOnClickListener() {
            var intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
            finish()
        }


    }
}