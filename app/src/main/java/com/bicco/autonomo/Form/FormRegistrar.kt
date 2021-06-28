package com.bicco.autonomo.Form

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.bicco.autonomo.Form.Login.Biccorequests
import com.bicco.autonomo.Form.Registro.validarfields
import com.bicco.autonomo.databinding.ActivityFormRegistrarBinding
import java.util.*


class FormRegistrar : AppCompatActivity(), DatePickerDialog.OnDateSetListener,
    AdapterView.OnItemSelectedListener {

    var day = 0
    var month = 0
    var year = 0
    lateinit var binding: ActivityFormRegistrarBinding
    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    lateinit var txtData: TextView
    lateinit var dataNascimento : String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFormRegistrarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        dataNascimento = ""
        var btt_register = binding.bttRegistrar
        var text_login = binding.textTelaLogin
        txtData = binding.txtDate

        var spinner = binding.listCategorias

        var myArray = arrayOf<String>(
            "Administrador",
            "Administrador público",
            "Agropecuarista",
            "Contabilista",
            "Economista",
            "Especialista em comércio exterior",
            "Chef",
            "Gerente comercial",
            "Gestor de recursos humanos",
            "Gestor de turismo",
            "Gestor público",
            "Hoteleiro",
            "Piloto de avião",
            "Turismólogo",
            "Artes e Design",
            "Animador",
            "Arquiteto",
            "Artista plástico",
            "Ator",
            "Dançarino",
            "Designer",
            "Designer de games",
            "Designer de interiores",
            "Designer de moda",
            "Fotógrafo",
            "Historiador da arte",
            "Músico",
            "Produtor cênico",
            "Produtor fonográfico",
            "Ciências Biológicas e da Terra",
            "Agrônomo",
            "Bioquímico",
            "Biotecnólogo",
            "Ecologista",
            "Geofísico",
            "Geólogo",
            "Gestor ambiental",
            "Veterinário",
            "Meteorologista",
            "Oceanógrafo",
            "Zootecnólogo",
            "Ciências Exatas e Informática",
            "Analista e desenvolvedor de sistemas",
            "Astrônomo",
            "Cientista da computação",
            "Estatístico",
            "Físico",
            "Gestor da tecnologia da informação",
            "Matemático",
            "Nanotecnólogo",
            "Químico",
            "Ciências Sociais e Humanas",
            "Advogado",
            "Arqueólogo",
            "Cooperativista",
            "Filósofo",
            "Geógrafo",
            "Historiador",
            "Linguista",
            "Museologista",
            "Pedagogo",
            "Professor",
            "Psicopedagogo",
            "Relações internacionais",
            "Sociólogo",
            "Teólogo",
            "Tradutor e intérprete",
            "Comunicação e Informação",
            "Arquivologista",
            "Biblioteconomista",
            "Educomunicador",
            "Jornalista",
            "Produtor audiovisual",
            "Produtor cultural",
            "Produtor editorial",
            "Produtor multimídia",
            "Produtor publicitário",
            "Publicitário",
            "Radialista",
            "Relações públicas",
            "Secretária",
            "Secretária executiva",
            "Engenharia e Produção",
            "Agricultor",
            "Construtor civil",
            "Construtor naval",
            "Engenheiro acústico",
            "Engenheiro aeronáutico",
            "Engenheiro agrícola",
            "Engenheiro ambiental e sanitário",
            "Engenheiro biomédico",
            "Engenheiro civil",
            "Engenheiro da computação",
            "Engenheiro de alimentos",
            "Engenheiro de biossistemas",
            "Engenheiro de controle e automação",
            "Engenheiro de energia",
            "Engenheiro de inovação",
            "Engenheiro de materiais",
            "Engenheiro de minas",
            "Engenheiro de pesca",
            "Engenheiro de petróleo",
            "Engenheiro de produção",
            "Engenheiro de segurança do trabalho",
            "Engenheiro de sistemas",
            "Engenheiro de software",
            "Engenheiro de telecomunicações",
            "Engenheiro de transporte e mobilidade",
            "Engenheiro elétrico",
            "Engenheiro eletrônico",
            "Engenheiro físico",
            "Engenheiro florestal",
            "Engenheiro hídrico",
            "Engenheiro mecânico",
            "Engenheiro mecatrônico",
            "Engenheiro naval",
            "Engenheiro químico",
            "Gestor da qualidade",
            "Minerador",
            "Silvicultor",
            "Saúde e Bem-Estar",
            "Biomédico",
            "Dentista",
            "Educador físico",
            "Enfermeiro",
            "Esteticista",
            "Farmacêutico",
            "Fisioterapeuta",
            "Fonoaudiólogo",
            "Gerontólogo",
            "Gestor em saúde",
            "Gestor hospitalar",
            "Médico",
            "Musicoterapeuta",
            "Nutricionista",
            "Psicólogo",
            "Radiologista",
            "Terapeuta ocupacional"
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
            val thread = Thread {
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
                    ListaDeCampos.add(valor_campo)


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
                            "foto",
                            categoria,
                            desc_campo,
                            0,
                            0,
                            0f,
                            0F
                        )

                        var intent = Intent(this, FormLogin::class.java)
                        startActivity(intent)
                        finish()
                    }

                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            thread.start()

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
    fun showToast(texto: String){
        runOnUiThread {
            Toast.makeText(
                this,
                texto,
                Toast.LENGTH_SHORT
            ).show()
        }

    }


}

    

