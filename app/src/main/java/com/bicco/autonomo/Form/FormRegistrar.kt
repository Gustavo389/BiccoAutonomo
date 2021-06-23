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
        var edt_date = binding.editTextDate
        var edt_cpf = binding.edtCpf
        var edt_telefone = binding.edtNumeroTel
        var box_list_profissoes = binding.listaProfissoes

        val sizeArray = 4

        text_login.setOnClickListener() {
            var intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
            finish()
        }


    }

    private fun Array(size: Int): Any {
        TODO("Not yet implemented")
    }

}

private operator fun Any.compareTo(i: Int): Int {
    TODO("Not yet implemented")
}
