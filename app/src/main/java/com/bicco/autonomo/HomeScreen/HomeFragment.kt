package com.bicco.autonomo.HomeScreen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bicco.autonomo.Form.Login.Biccorequests
import com.bicco.autonomo.Form.Login.dadosAutonomo
import com.bicco.autonomo.R
import com.bicco.autonomo.databinding.FragmentHomeBinding
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder

private lateinit var adapter: GroupAdapter<ViewHolder>

class HomeFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Tela Inicial"
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val binding = FragmentHomeBinding.inflate(layoutInflater)
//        var recyclerViewHome = binding.re
//        adapter = GroupAdapter()
//        recyclerViewHome.adapter = adapter
        var botao1 = binding.txtRedirectViana

        val thread = Thread {
            try {
                var idperfil = Identificacao.identificacao
                val list = Biccorequests.verAutonomo("https://bicco-api.herokuapp.com/ver/autonomo", idperfil)
                var listSize = list.size
                var dado = dadosAutonomo()
                if (listSize != 0) { // sucesso
                    dado.nome = list[0]
                    dado.email = list[1]
                    dado.senha = list[2]
                    dado.datanasc = list[3]
                    dado.cpf = list[4]
                    dado.tel = list[5]
                    dado.foto = list[6]
                    dado.categoria = list[7]
                    dado.descricao = list[8]
                    dado.plano = list[9].toInt()
                    dado.pedidos = list[10].toInt()
                    dado.preco = list[11].toFloat()
                    dado.avaliacao = list[12].toFloat()
                    Identificacao.setDados(
                        dado.nome,
                        dado.email,
                        dado.senha,
                        dado.datanasc,
                        dado.cpf,
                        dado.tel,
                        dado.foto,
                        dado.categoria,
                        dado.descricao,
                        dado.plano,
                        dado.pedidos,
                        dado.preco,
                        dado.avaliacao
                    )
                    println("nome "+dado.nome)



                } else {
                    println("erro")
                    // erro
                }


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        thread.start()





        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        println("NOME"+ Identificacao.nome)

        super.onActivityCreated(savedInstanceState)
    }



}





