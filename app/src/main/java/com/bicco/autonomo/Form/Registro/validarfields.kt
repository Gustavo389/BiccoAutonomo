package com.bicco.autonomo.Form.Registro

import android.view.LayoutInflater
import com.bicco.autonomo.Form.FormRegistrar
import com.bicco.autonomo.databinding.ActivityFormRegistrarBinding
import java.util.*

object validarfields {
    var retorno : Int = 0
    fun validarCampos(Lista: ArrayList<String>): Int {
        for (item in Lista) {
            if (item.isEmpty()) {
                retorno = 1
            }
            else{
                retorno = 0
            }
        }
        return retorno
    }

//    @JvmStatic
//    fun main(args: Array<String>) {
//        val viewGroup = null
//        val binding: ActivityFormRegistrarBinding = ActivityFormRegistrarBinding.inflate(getLayoutInflater(), viewGroup, false)
//        val ListaDeCampos = ArrayList<String>()
//        ListaDeCampos.add(binding.toString())
//        val situacao = validarCampos(ListaDeCampos)
//        if (situacao == 1) {
//            println("campos nulos")
//        } else {
//            println("campos OK")
//        }
//    }
//
//    private fun getLayoutInflater(): LayoutInflater {
//        TODO("Not yet implemented")
//    }
}