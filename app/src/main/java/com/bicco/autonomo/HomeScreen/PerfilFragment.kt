package com.example.abalateral

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bicco.autonomo.HomeScreen.Identificacao

import com.bicco.autonomo.databinding.FragmentPerfilBinding

class PerfilFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Perfil"
        val binding = FragmentPerfilBinding.inflate(inflater, container, false)
        var nome_fragment = binding.nomePerfilAutonomoFrag
        var cpf_fragment = binding.cpfPerfilAutonomoFrag
        var email_fragment = binding.emailPerfilAutonomoFragment
        var telefone_fragment = binding.telefonePerfilAutonomoFrag
        var editar_botao_fragment = binding.bttEditarPerfilFrag

                    println("--------------------------Dentro")
                    nome_fragment.setText(Identificacao.nome)
                    cpf_fragment.setText(Identificacao.cpf)
                    email_fragment.setText(Identificacao.email)
                    telefone_fragment.setText(Identificacao.telefone)
                    Identificacao.printResul()





        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
    }


}