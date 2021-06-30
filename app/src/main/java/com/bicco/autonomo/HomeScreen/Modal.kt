package com.bicco.autonomo.HomeScreen

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.bicco.autonomo.Form.FormLogin
import com.bicco.autonomo.Form.FormRegistrar
import com.bicco.autonomo.geradorDeSenha.gerador
import kotlinx.coroutines.Job
import kotlinx.coroutines.*
import kotlinx.coroutines.launch

class modal : AppCompatActivity() {
    override fun onBackPressed() {
        val modal: Any? = null

        val alert = AlertDialog.Builder(
            (modal as Context?)!!
        )
        alert.setTitle("Do you want to logout?")
        // alert.setMessage("Message");
        alert.setPositiveButton("Ok") { dialog, whichButton ->
            //Your action here
        }
        alert.setNegativeButton(
            "Cancel"
        ) { dialog, whichButton -> }
        alert.show()
    }

    fun janelaModal() {


        val modal: Any? = null

        val alert = androidx.appcompat.app.AlertDialog.Builder(this)

            val SenhadeRecuperacao = gerador.getRandomPass()
            alert.setTitle("Chave de recuperação")
            alert.setMessage("Por motivos de segurança, anote seus números de recuperação de senha: $SenhadeRecuperacao.");
            alert.setPositiveButton("Já anotei meu número de recuperação de senha") { dialog, whichButton ->
                var intent = Intent(FormRegistrar().applicationContext, FormLogin::class.java)
                startActivity(intent)
                finish()
            }



        val dialog: androidx.appcompat.app.AlertDialog = alert.create()
        dialog.show()
    }
}