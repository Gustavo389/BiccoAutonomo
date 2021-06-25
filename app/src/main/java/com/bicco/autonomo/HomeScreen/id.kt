package com.bicco.autonomo.HomeScreen

import android.os.Bundle

object Identificacao {
     var identificacao: Int = 0
    fun onCreate(savedInstanceState: Bundle?) {


    }
    @JvmName("setId1")
    fun setId(id: Int){
      this.identificacao = id
    }
}