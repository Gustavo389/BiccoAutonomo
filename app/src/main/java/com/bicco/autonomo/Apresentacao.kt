package com.bicco.autonomo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.bicco.autonomo.Form.FormLogin
import com.bicco.autonomo.Form.FormRegistrar
import com.heinrichreimersoftware.materialintro.app.IntroActivity

class Apresentacao : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_apresentacao)

        var botao_login: Button = findViewById(R.id.btt_login)
        var botao_registrar: Button = findViewById(R.id.btt_registrar)

        botao_login.setOnClickListener{
            var intent = Intent(this, FormLogin::class.java)
            startActivity(intent)

        }
        botao_registrar.setOnClickListener(){
        var intent = Intent(this, FormRegistrar::class.java)
        startActivity(intent)

    }}
}