package com.bicco.autonomo.Form.Login

import android.widget.TextView
import com.bicco.autonomo.HomeScreen.Identificacao
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
                Identificacao.setDados(list[0],list[1],list[2],list[3],list[4],list[5],list[6],list[7],list[8],list[9].toInt(),list[10].toInt(),list[11].toFloat(),list[12].toFloat())


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
    fun numeroDeFotos(url: String?, id: Int): Int {
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
            val resposta =
                converter.fromJson(response, totalFotos::class.java)
            conn.disconnect()
            `in`.close()
            return resposta.total
        } else {
            // adicionar aqui um popup de erro
            println("Não foi possivel acessar seu perfil")
            conn.disconnect()
            `in`.close()
        }
        return 0
    }

    @Throws(IOException::class)
    fun adicionarFoto(url: String?, id: Int, foto: String?): String {
        val gson = GsonBuilder().create()
        val usuario = adicionarFoto()
        usuario.id = id
        usuario.foto = foto
        val json = gson.toJson(usuario, adicionarFoto::class.java)
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
            val validacao = resposta.mensagem
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

    @Throws(IOException::class)
    fun deletarFoto(url: String?, id: Int, foto: String?): String {
        val gson = GsonBuilder().create()
        val usuario = adicionarFoto()
        usuario.id = id
        usuario.foto = foto
        val json = gson.toJson(usuario, adicionarFoto::class.java)
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
            val validacao = resposta.mensagem
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
    fun verTodosPortfolio(url: String?, id: Int) {
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
            val resposta = converter.fromJson(response, fotosPortfolio::class.java)
            conn.disconnect()
            `in`.close()

            // for imprimi todas as fotos do portfolio do autonomo
            for (foto in resposta.fotos) {
                println(foto)
            }
        } else {

            // adicionar popup de erro
            conn.disconnect()
            `in`.close()
        }
    }

    @Throws(IOException::class)
    fun definirSenha(url: String?, id: Int, sequencia: String?): String {
        val gson = GsonBuilder().create()
        val dados = definirSenha()
        dados.id = id
        dados.sequencia = sequencia
        val json = gson.toJson(dados, definirSenha::class.java)
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
            val validacao = resposta.mensagem
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

    @Throws(IOException::class)
    fun recuperarSenha(url: String?, senha: String?): Int {
        val gson = GsonBuilder().create()
        val dados = recuperarSenha()
        dados.senha = senha
        val json = gson.toJson(dados, recuperarSenha::class.java)
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
            val resposta = converter.fromJson(response, buscasId::class.java)
            conn.disconnect()
            `in`.close()
            val id = resposta.id
            if (id != 0) {
                id
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
    @JvmStatic
    fun main(args: Array<String>) {

// 		LOGIN
        //
        ////		retorna um id se diferente de nulo acesso permitido se nao erro ou acesso negado
        ////		int id = Biccorequests.login("https://bicco-api.herokuapp.com/login/autonomo", "felipeneto4@hotmail.com.br", "123");
        //
        //
        ////		retorna um id se diferente de nulo acesso permitido se nao erro ou acesso negado
        ////		int id = Biccorequests.login("https://bicco-api.herokuapp.com/login/cliente", "felipeneto4@hotmail.com.br","123");
        //
        //// 		CADASTRO
        //
        ////		retorna sucesso ou error, se retornar 00000 significa erro no servidor
        ////		String situacao = Biccorequests.cadastrarAutonomo("https://bicco-api.herokuapp.com/cadastrar/autonomo",
        ////				"Felipe Neto","felipeneto4@hotmail.com.br","123", "1987-09-18",
        ////				"91412337659", "21 89798-0909", "0","pasteleiro",
        ////				"Faco pastel na netolandia", 1, 0, 100.00f, 0.0f);
        //
        ////		retorna sucesso ou error, se retornar 00000 significa erro no servidor
        ////		String situacao = Biccorequests.cadastrarCliente("https://bicco-api.herokuapp.com/cadastrar/cliente",
        ////				"Felipe Neto","felipeneto4@hotmail.com.br","123", "1987-09-18",
        ////				  "91412337659", "21 89798-0909", "0");
        ////
        //
        //// 		CARREGAR FEED DE AUTONOMOS
        //
        ////		metodo não retorna nada (chamar o metodo assim que o login cliente ser feito para montar o feed)
        ////		Biccorequests.verTodos("https://bicco-api.herokuapp.com/ver/todos");
        //
        //
        ////		VER DADOS DE PERFIl
        //
        ////		Biccorequests.verCliente("https://bicco-api.herokuapp.com/ver/cliente", 2);
        ////		Biccorequests.verAutonomo("https://bicco-api.herokuapp.com/ver/autonomo", 13);
        //
        ////		EXCLUIR PERFIL
        //
        ////		String situacao = Biccorequests.deletar("https://bicco-api.herokuapp.com/deletar/autonomo", 13);
        ////		String situacao = Biccorequests.deletar("https://bicco-api.herokuapp.com/deletar/cliente", 2);
        //
        //
        ////		---------------------------------
        //
        ////		VER TOTAL DE FOTOS NO PORTFOLIO
        //
        ////		metodo retorna numero total de fotos no portfolio
        ////		int total = Biccorequests.numeroDeFotos("https://bicco-api.herokuapp.com/portfolio/contar", 13);
        //
        ////		ADICIONAR, DELETAR, VER TODAS IMAGENS PORTFOLIO
        //
        ////		metodo retorna mensagem de "sucesso" ou "error", "00000" -> erro na request
        ////		String situacao = Biccorequests.adicionarFoto("https://bicco-api.herokuapp.com/portfolio/adicionar", 1, "a2b1");
        //
        ////		metodo retorna mensagem de "sucesso" ou "error", "00000" -> erro na request
        ////		String situacao = Biccorequests.deletarFoto("https://bicco-api.herokuapp.com/portfolio/deletar", 1, "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");
        //
        ////		metodo não retorna nada (chamar o metodo quando a tela de portfolio for chamada)
        ////		Biccorequests.verTodosPortfolio("https://bicco-api.herokuapp.com/portfolio/ver", 1);
        //
        //
        ////		SENHA DE SEGURANÇA
        //
        ////		String codigo = gerador.getRandomPass(); // gera a senha de 10 digitos (mostrar ela na hora de cadastro para o usuario anotar)
        //
        //        // para definir a senha do cliente so alterar na url o autonomo para cliente
        ////		String situacao = Biccorequests.definirSenha("https://bicco-api.herokuapp.com/senha/autonomo/definir",1, codigo);
        //        // o metodo retorna sucesso, error ou 00000
        //
        //        // para recuperar a senha do cliente so alterar na url o autonomo para cliente
        ////		int id = Biccorequests.recuperarSenha("https://bicco-api.herokuapp.com/senha/autonomo/recuperar", "HiIJmYRVeV");
        //        // retorna o id de usuario, se retorna 0 pode ser erro na request ou senha invalida
        println("BICCO FAZ UM BICCO AI!")
    }
}