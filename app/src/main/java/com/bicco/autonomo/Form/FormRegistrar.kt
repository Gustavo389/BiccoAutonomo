package com.bicco.autonomo.Form


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bicco.autonomo.Form.Login.Biccorequests
import com.bicco.autonomo.Form.Registro.validarfields
import com.bicco.autonomo.databinding.ActivityFormRegistrarBinding
import com.bicco.autonomo.geradorDeSenha.gerador
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.Runnable
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.util.*


class FormRegistrar : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    AdapterView.OnItemSelectedListener {
    private var SelecionarUri: Uri? = null
    var day = 0
    var month = 0
    var year = 0
    lateinit var binding: ActivityFormRegistrarBinding
    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    lateinit var txtData: TextView
    lateinit var dataNascimento: String
    lateinit var imageString : String



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataNascimento = ""
        var btt_register = binding.bttRegistrar
        var text_login = binding.textTelaLogin
        txtData = binding.txtDate
        var btton_Img = binding.imgButtonPerfil

        var spinner = binding.listCategorias

        var myArray = arrayOf<String>(
            "",
            "Artes e Design",
            "Aulas particulares",
            "Assistência Técnica",
            "Culinária e confeitaria",
            "Consultoria e Vendas",
            "Eventos",
            "Música e Teatro",
            "Serviços Domésticos",
            "Outros",
            "Reformas e reparos",
            "Saúde e Bem-Estar"
        )
        pickDate()
        // <Caixa de seleção de texto
        spinner!!.setOnItemSelectedListener(this).toString()

        // Create an ArrayAdapter using a simple spinner layout and languages array
        val array_adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, myArray)
        // Set layout to use when the list of choices appear
        array_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        // Set Adapter to Spinner
        spinner!!.setAdapter(array_adapter)
        // Fechamento/>


        text_login.setOnClickListener() {
            var intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
            finish()
        }

        btt_register.setOnClickListener() {
            val scope = CoroutineScope(Job())
            scope.launch {
                try {
                    //Your code goes here
                    var email_campo = binding.editEmailRegister.text.toString()
                    var senha_campo = binding.editSenhaRegister.text.toString()
                    var nome_campo = binding.edtNome.text.toString()
                    var desc_campo = binding.edtDescServ.text.toString()
                    var valor_campo = binding.valorHora.text.toString()
                    var categoria = spinner.selectedItem.toString()
                    var cpf_campo = binding.edtCpf.text.toString()
                    var telefone_campo = binding.edtNumeroTel.text.toString()
                    var valorPhora_campo = binding.valorHora.text.toString()

                    println("email $email_campo")
                    println("senha $senha_campo")
                    println("nome $nome_campo")
                    println("cpf " + cpf_campo)
                    println("telefone " + telefone_campo)
                    println("descrição $desc_campo")
                    println("data $dataNascimento")
                    println("categoria $categoria")
                    println("valor $valor_campo")
                    val ListaDeCampos = ArrayList<String>()
                    ListaDeCampos.add(nome_campo)
                    ListaDeCampos.add(email_campo)
                    ListaDeCampos.add(senha_campo)
                    ListaDeCampos.add(cpf_campo)
                    ListaDeCampos.add(telefone_campo)
                    ListaDeCampos.add(desc_campo)
                    ListaDeCampos.add(categoria)
                    ListaDeCampos.add(desc_campo)
                    ListaDeCampos.add(valorPhora_campo)


                    val situacao = validarfields.validarCampos(ListaDeCampos)
                    println(situacao)
                    if (situacao == 1) {
                        println("campos nulos")
                        showToast("Campo Nulo")
                    } else {
                        println("campos OK")

                        showToast("Registro feito com sucesso!")
                        Biccorequests.cadastrarAutonomo(
                            "https://bicco-api.herokuapp.com/cadastrar/autonomo",
                            nome_campo,
                            email_campo,
                            senha_campo,
                            dataNascimento,
                            cpf_campo,
                            telefone_campo,
                            imageString,
                            categoria,
                            desc_campo,
                            0,
                            0,
                            valor_campo.toFloat(),
                            0F
                        )
                        runOnUiThread(Runnable {
                            println("Thread : {${Thread.currentThread().name}")
                            janelaModal()


                        })


                    }


                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }


        }
        btton_Img.setOnClickListener() {
            selecionarFotoDaGaleria()
        }

    }


    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        var image = binding.imgButtonPerfil

        if (requestCode == 0) {
            SelecionarUri = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, SelecionarUri)
            image.setImageBitmap(bitmap)
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            var imageBytes: ByteArray = baos.toByteArray()
            imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT)
            Log.d("CODED", "$imageString")


        }
    }

    override fun onItemSelected(arg0: AdapterView<*>, arg1: View, position: Int, id: Long) {

        // use position to know the selected item
    }

    override fun onNothingSelected(arg0: AdapterView<*>) {

    }


    private fun getDateTimeCalendar() {
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)

    }

    private fun pickDate() {
        val txt_data = binding.txtDate
        txt_data.setOnClickListener {
            getDateTimeCalendar()
            DatePickerDialog(this, this, year, month, day).show()
        }

    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {
        savedDay = dayOfMonth
        savedMonth = month + 1
        savedYear = year
        txtData = binding.txtDate
        if (savedDay >= 10) { // acima ou igual 10
            if (savedMonth >= 10) {
                txtData.setText("$savedYear-$savedMonth-$savedDay") //acima ou igual 10
                dataNascimento = txtData.text.toString()
                println(dataNascimento)
            }

            if (savedMonth < 10) {
                txtData.setText("$savedYear-0$savedMonth-$savedDay") //abaixo = 0+mês
                dataNascimento = txtData.text.toString()
                println(dataNascimento)
            }
        }
        if (savedDay < 10) {     // abaixo = 0+ dia
            if (savedMonth < 10) {// abaixo = 0+ dia
                txtData.setText("$savedYear-0$savedMonth-0$savedDay")
                dataNascimento = txtData.text.toString()
                println(dataNascimento)
            }
            if (savedMonth >= 10) {// acima ou igual a 10
                txtData.setText("$savedYear-$savedMonth-0$savedDay")
                dataNascimento = txtData.text.toString()
                println(dataNascimento)
            }
        }

    }

    fun showToast(texto: String) {
        runOnUiThread {
            Toast.makeText(
                this,
                texto,
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    fun janelaModal() {
        val alert = androidx.appcompat.app.AlertDialog.Builder(this)
        val SenhadeRecuperacao = gerador.getRandomPass()
        alert.setTitle("Chave de recuperação")
        alert.setMessage("Por motivos de segurança, anote seus números de recuperação de senha: $SenhadeRecuperacao.");
        alert.setPositiveButton("Já anotei meu número de recuperação de senha") { dialog, whichButton ->
            var intent = Intent(this, FormLogin::class.java)
            startActivity(intent)
            finish()
        }


        val dialog: androidx.appcompat.app.AlertDialog = alert.create()
        dialog.show()

        val dentroScope = CoroutineScope(Job())
        dentroScope.launch {
            val email_campScope = binding.editEmailRegister.text.toString()
            val senha_campScope = binding.editSenhaRegister.text.toString()
            var validandoDados = Biccorequests.login(
                "https://bicco-api.herokuapp.com/login/autonomo",
                email_campScope,
                senha_campScope
            )
            println()
            Biccorequests.definirSenha(
                "https://bicco-api.herokuapp.com/senha/autonomo/definir",
                validandoDados,
                SenhadeRecuperacao
            )

        }
    }

    private fun selecionarFotoDaGaleria() {

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 0)
    }


}

    

