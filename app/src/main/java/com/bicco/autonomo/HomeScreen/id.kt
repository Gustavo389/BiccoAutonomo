package com.bicco.autonomo.HomeScreen

import android.os.Bundle

object Identificacao {
    var identificacao: Int = 0
    var nome:String = ""
    var email: String  = ""
    var senha: String = ""
    var datanasc: String = ""
    var cpf: String = ""
    var telefone: String = ""
    var foto: String = ""
    var categoria: String = ""
    var descricao: String = ""
    var plano: Int = 0
    var pedidos: Int = 0
    var preco: Float = 0f
    var avaliacao: Float = 0F
    fun onCreate(savedInstanceState: Bundle?) {


    }
    @JvmName("setId1")
    fun setId(id: Int){
      this.identificacao = id
    }
    fun setDados(nome: String, email: String, senha: String, datanasc: String, cpf: String, telefone: String, foto: String, categoria: String, descricao: String, plano: Int, pedidos: Int, preco: Float, avaliacao: Float){
        this.nome = nome
        this.email = email
        this.senha = senha
        this.datanasc = datanasc
        this.cpf = cpf
        this.telefone = telefone
        this.foto = foto
        this.categoria = categoria
        this.descricao = descricao
        this.plano = plano
        this.pedidos =  pedidos
        this.preco = preco
        this.avaliacao = avaliacao
    }
    fun printResul(){
        println("Teste -----$nome,$email,$senha,$datanasc,$cpf,$telefone,$foto,$categoria,$descricao,$plano,$pedidos,$preco,$avaliacao")
    }
}