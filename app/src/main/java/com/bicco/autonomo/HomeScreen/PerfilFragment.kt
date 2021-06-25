package com.example.abalateral

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bicco.autonomo.Form.Login.Biccorequests
import com.bicco.autonomo.HomeScreen.Identificacao
import com.bicco.autonomo.R
import com.bicco.autonomo.databinding.FragmentPerfilBinding

class PerfilFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?


    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Perfil"
        return inflater.inflate(R.layout.fragment_perfil, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val thread = Thread {
            try {
                var binding = FragmentPerfilBinding.inflate(layoutInflater)
                var foto_fragment = binding.fotoPerfilFrag
                var nome_fragment = binding.nomePerfilAutonomoFrag
                var cpf_fragment = binding.cpfPerfilAutonomoFrag
                var email_fragment = binding.emailPerfilAutonomoFragment
                var telefone_fragment = binding.telefonePerfilAutonomoFrag
                var editar_botao_fragment = binding.bttEditarPerfilFrag
                println(Identificacao.identificacao)
                var idperfil = Identificacao.identificacao
                Biccorequests.verAutonomo("https://bicco-api.herokuapp.com/ver/autonomo", idperfil)


            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        thread.start()





        super.onViewCreated(view, savedInstanceState)
    }


}