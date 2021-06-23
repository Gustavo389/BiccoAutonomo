package pacotebicco

import android.os.Build
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import variableClass.dadosLogin
import variableClass.validarLogin
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL


object fazerLogin {
    @Throws(IOException::class, InterruptedException::class)
    fun login(url: String?, email: String?, senha: String?): Int {
        if (Build.VERSION.SDK_INT > 9) {
            val policy = ThreadPolicy.Builder().permitAll().build()
            StrictMode.setThreadPolicy(policy)
        }
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
        val `in` = BufferedReader(InputStreamReader(
                conn.inputStream))
        val status = conn.responseCode

        if (status == 200) {


            val response = `in`.readLine()
            val converter = Gson()
            val resposta = converter.fromJson(response, validarLogin::class.java)
            println(response)

            if (resposta.acesso == "true") {
                println("Acesso " + resposta.acesso)
                println("Emai " + resposta.email)
                println("ide " + resposta.id)
                println("senha " + resposta.senha)
                return resposta.id

        } else {
            conn.disconnect()
            `in`.close()
            return 1000
        }
    };
    return 0
    }}

//    @Throws(IOException::class, InterruptedException::class)
//    @JvmStatic
//    fun main(args: Array<String>) {
//        val Id = login("https://bicco-api.herokuapp.com/login/autonomo", "thiaguin22@gmail.com", "minecraft")
//        println(Id)
//        if (Id != 0) {
//            println("-----------")
//            println("Bem vindo")
//        } else {
//            println("algo de errado nao esta certo")
//        }
//    }}
