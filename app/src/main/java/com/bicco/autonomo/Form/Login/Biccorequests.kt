package com.bicco.autonomo.Form.Login

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object Biccorequests {
    @Throws(IOException::class, InterruptedException::class)
    fun login(url: String?, email: String?, senha: String?): Int {
        val gson = GsonBuilder().create()
        val user = dadosLogin()
        user.email = email
        user.senha = senha
        val json = gson.toJson(user, dadosLogin::class.java)
        val obj = URL(url)
        val conn = obj.openConnection() as HttpURLConnection
        conn.connectTimeout = 5000
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
        conn.doOutput = true
        conn.doInput = true
        conn.requestMethod = "POST"
        val os = conn.outputStream
        os.write(json.toByteArray(charset("UTF-8")))
        os.close()
        val `in` = BufferedReader(
            InputStreamReader(
                conn.inputStream
            )
        )
        val status = conn.responseCode
        return if (status == HttpURLConnection.HTTP_OK) {
            val response = `in`.readLine()
            val converter = Gson()
            val resposta = converter.fromJson(response, validarLogin::class.java)
            conn.disconnect()
            `in`.close()
            if (resposta.isAcesso) {
                println("Acesso " + resposta.isAcesso)
                println("Email " + resposta.email)
                println("Id " + resposta.id)
                println("Senha " + resposta.senha)
                resposta.id
            } else {
                0
            }
        } else {
            conn.disconnect()
            `in`.close()
            0
        }
    }

    @Throws(IOException::class, InterruptedException::class)
    fun cadastrarAutonomo(
        url: String?,
        nome: String?,
        email: String?,
        senha: String?,
        datanasc: String,
        cpf: String?,
        tel: String?,
        foto: String?,
        categoria: String?,
        descricao: String?,
        plano: Int,
        pedidos: Int,
        preco: Float,
        avaliacao: Float
    ): String {
        val gson = GsonBuilder().create()
        val usuario = dadosAutonomo()
        usuario.nome = nome
        usuario.email = email
        usuario.senha = senha
        usuario.datanasc = datanasc
        usuario.cpf = cpf
        usuario.tel = tel
        usuario.foto = foto
        usuario.categoria = categoria
        usuario.descricao = descricao
        usuario.plano = plano
        usuario.pedidos = pedidos
        usuario.preco = preco
        usuario.avaliacao = avaliacao
        val json = gson.toJson(usuario, dadosAutonomo::class.java)
        val obj = URL(url)
        val conn = obj.openConnection() as HttpURLConnection
        conn.connectTimeout = 5000
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
        conn.doOutput = true
        conn.doInput = true
        conn.requestMethod = "POST"
        val os = conn.outputStream
        os.write(json.toByteArray(charset("UTF-8")))
        os.close()
        val `in` = BufferedReader(
            InputStreamReader(
                conn.inputStream
            )
        )
        val status = conn.responseCode
        return if (status == HttpURLConnection.HTTP_OK) {
            val response = `in`.readLine()
            val converter = Gson()
            val resposta = converter.fromJson(response, validadorGlobal::class.java)
            conn.disconnect()
            `in`.close()
            val validacao = resposta.getMensagem()
            if (validacao == "sucesso") {
                "sucesso"
            } else {
                "error"
            }
        } else {
            conn.disconnect()
            `in`.close()
            "00000"
        }
    }

    @Throws(IOException::class, InterruptedException::class)
    fun cadastrarCliente(
        url: String?,
        nome: String?,
        email: String?,
        senha: String?,
        datanasc: String?,
        cpf: String?,
        tel: String?,
        foto: String?
    ): String {
        val gson = GsonBuilder().create()
        val usuario = dadosCliente()
        usuario.nome = nome
        usuario.email = email
        usuario.senha = senha
        usuario.datanasc = datanasc
        usuario.cpf = cpf
        usuario.tel = tel
        usuario.foto = foto
        val json = gson.toJson(usuario, dadosCliente::class.java)
        val obj = URL(url)
        val conn = obj.openConnection() as HttpURLConnection
        conn.connectTimeout = 5000
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
        conn.doOutput = true
        conn.doInput = true
        conn.requestMethod = "POST"
        val os = conn.outputStream
        os.write(json.toByteArray(charset("UTF-8")))
        os.close()
        val `in` = BufferedReader(
            InputStreamReader(
                conn.inputStream
            )
        )
        val status = conn.responseCode
        return if (status == HttpURLConnection.HTTP_OK) {
            val response = `in`.readLine()
            val converter = Gson()
            val resposta = converter.fromJson(response, validadorGlobal::class.java)
            conn.disconnect()
            `in`.close()
            val validacao = resposta.getMensagem()
            if (validacao == "sucesso") {
                "sucesso"
            } else {
                "error"
            }
        } else {
            conn.disconnect()
            `in`.close()
            "00000"
        }
    }

    @Throws(IOException::class, InterruptedException::class)
    fun verTodos(url: String?) {

        // faz a request
        val obj = URL(url)
        val conn = obj.openConnection() as HttpURLConnection
        conn.requestMethod = "GET"
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
        val responseCode = conn.responseCode
        if (responseCode == HttpURLConnection.HTTP_OK) { // success

            // pega a resposta e converte para uma classe
            val `in` = BufferedReader(
                InputStreamReader(
                    conn.inputStream
                )
            )
            val response = `in`.readLine()
            val converter = Gson()
            val resposta = converter.fromJson(response, feedAutonomos::class.java)
            println("-------------")

            // pega o tamanho do array pago e gratuito para iterar sob
            val gratuitoSize = resposta.getGratuito().size
            val pagoSize = resposta.getPago().size

            // itera sobre os autonomos pagos
            for (count in 0 until pagoSize) {
                println(resposta.getPago()[count].email)
                println(resposta.getPago()[count].senha)
                println("-------------")
            }
            // itera sobre os autonomos gratuitos
            for (count in 0 until gratuitoSize) {
                println(resposta.getGratuito()[count].email)
                println(resposta.getGratuito()[count].senha)
                println("-------------")
            }
        } else {
            // adicionar aqui um popup de erro
            println("Não foi possivel acessar o feed")
        }
    }

    @Throws(IOException::class, InterruptedException::class)
    fun verCliente(url: String?, id: Int) {
        val gson = GsonBuilder().create()
        val idUsuario = buscasId()
        idUsuario.id = id
        val json = gson.toJson(idUsuario, buscasId::class.java)
        val obj = URL(url)
        val conn = obj.openConnection() as HttpURLConnection
        conn.connectTimeout = 5000
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
        conn.doOutput = true
        conn.doInput = true
        conn.requestMethod = "POST"
        val os = conn.outputStream
        os.write(json.toByteArray(charset("UTF-8")))
        os.close()
        val `in` = BufferedReader(
            InputStreamReader(
                conn.inputStream
            )
        )
        val status = conn.responseCode
        if (status == HttpURLConnection.HTTP_OK) {
            val response = `in`.readLine()
            val converter = Gson()
            val resposta = converter.fromJson(response, dadosCliente::class.java)
            conn.disconnect()
            `in`.close()
            val validacao = resposta.email
            if (validacao != null) {
                println("Olá " + resposta.nome)
            } else {
                // adicionar aqui um popup de erro
                println("Não foi possivel acessar seu perfil")
            }
        } else {
            // adicionar aqui um popup de erro
            println("Não foi possivel acessar seu perfil")
            conn.disconnect()
            `in`.close()
        }
    }

    @Throws(IOException::class, InterruptedException::class)
    fun verAutonomo(url: String?, id: Int): MutableList<String> {
        val gson = GsonBuilder().create()
        val idUsuario = buscasId()
        idUsuario.id = id
        val json = gson.toJson(idUsuario, buscasId::class.java)
        val obj = URL(url)
        val conn = obj.openConnection() as HttpURLConnection
        conn.connectTimeout = 5000
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
        conn.doOutput = true
        conn.doInput = true
        conn.requestMethod = "POST"
        val os = conn.outputStream
        os.write(json.toByteArray(charset("UTF-8")))
        os.close()
        val `in` = BufferedReader(
            InputStreamReader(
                conn.inputStream
            )
        )
        val status = conn.responseCode
        val list: MutableList<String> = ArrayList()
        if (status == HttpURLConnection.HTTP_OK) {
            val response = `in`.readLine()
            val converter = Gson()
            println(response)
            val resposta = converter.fromJson(response, dadosAutonomo::class.java)
            conn.disconnect()
            `in`.close()
            val validacao = resposta.email

            if (validacao != null) {

                list.add(resposta.nome) // 0
                list.add(resposta.email)
                list.add(resposta.senha)
                list.add(resposta.datanasc)
                list.add(resposta.cpf)
                list.add(resposta.tel)
                list.add(resposta.foto)
                list.add(resposta.categoria)
                list.add(resposta.descricao)
                list.add(resposta.plano.toString())
                list.add(resposta.pedidos.toString())
                list.add(resposta.preco.toString())
                list.add(resposta.avaliacao.toString()) // 12



            } else {
                // adicionar aqui um popup de erro

            }
        } else {

            // adicionar aqui um popup de erro
            conn.disconnect()
            `in`.close()
        }
        return list
    }

    @Throws(IOException::class)
    fun deletar(url: String?, id: Int): String {
        val gson = GsonBuilder().create()
        val idUsuario = buscasId()
        idUsuario.id = id
        val json = gson.toJson(idUsuario, buscasId::class.java)
        val obj = URL(url)
        val conn = obj.openConnection() as HttpURLConnection
        conn.connectTimeout = 5000
        conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8")
        conn.doOutput = true
        conn.doInput = true
        conn.requestMethod = "POST"
        val os = conn.outputStream
        os.write(json.toByteArray(charset("UTF-8")))
        os.close()
        val `in` = BufferedReader(
            InputStreamReader(
                conn.inputStream
            )
        )
        val status = conn.responseCode
        return if (status == HttpURLConnection.HTTP_OK) {
            val response = `in`.readLine()
            val converter = Gson()
            val resposta = converter.fromJson(response, validadorGlobal::class.java)
            conn.disconnect()
            `in`.close()
            val validacao = resposta.getMensagem()
            if (validacao == "sucesso") {
                "sucesso"
            } else {
                "error"
            }
        } else {
            conn.disconnect()
            `in`.close()
            "00000"
        }
    }

    @Throws(IOException::class, InterruptedException::class)
    @JvmStatic
    fun main(args: Array<String>) {

// 		LOGIN

//		retorna um id se diferente de nulo acesso permitido se nao erro ou acesso negado
//		int id = Biccorequests.login("https://bicco-api.herokuapp.com/login/autonomo", "thiaguin21@gmail.com", "minecraft");

//		retorna um id se diferente de nulo acesso permitido se nao erro ou acesso negado
//		int id = Biccorequests.login("https://bicco-api.herokuapp.com/login/cliente", "felipeneto4@hotmail.com.br","123");

// 		CADASTRO

//		retorna sucesso ou error, se retornar 00000 significa erro no servidor
//		String situacao = Biccorequests.cadastrarAutonomo("https://bicco-api.herokuapp.com/cadastrar/autonomo",
//				"Felipe Neto","felipeneto4@hotmail.com.br","123", "1987-09-18",
//				"91412337659", "21 89798-0909", "0","pasteleiro",
//				"Faco pastel na netolandia", 1, 0, 100.00f, 0.0f);

//		retorna sucesso ou error, se retornar 00000 significa erro no servidor
//		String situacao = Biccorequests.cadastrarCliente("https://bicco-api.herokuapp.com/cadastrar/cliente",
//				"Felipe Neto","felipeneto4@hotmail.com.br","123", "1987-09-18",
//				  "91412337659", "21 89798-0909", "0");
//

// 		CARREGAR FEED DE AUTONOMOS

//		metodo não retorna nada (chamar o metodo assim que o login cliente ser feito para montar o feed)
//		Biccorequests.verTodos("https://bicco-api.herokuapp.com/ver/todos");


//		VER DADOS DE PERFIl

//		Biccorequests.verCliente("https://bicco-api.herokuapp.com/ver/cliente", 2);
//		Biccorequests.verAutonomo("https://bicco-api.herokuapp.com/ver/autonomo", 13);

//		EXCLUIR PERFIL
        val situacao = deletar("https://bicco-api.herokuapp.com/deletar/autonomo", 13)
        //		String situacao = Biccorequests.deletar("https://bicco-api.herokuapp.com/deletar/cliente", 2);
        println("BICCO FAZ UM BICCO AI!")
    }
}