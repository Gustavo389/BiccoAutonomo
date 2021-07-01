package com.example.abalateral

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Base64
import android.util.Base64.DEFAULT
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.bicco.autonomo.Apresentacao
import com.bicco.autonomo.Form.FormLogin
import com.bicco.autonomo.Form.Login.Biccorequests
import com.bicco.autonomo.HomeScreen.Identificacao
import com.bicco.autonomo.databinding.FragmentPerfilBinding
import com.bicco.autonomo.geradorDeSenha.gerador
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat


class PerfilFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Perfil"
        val binding = FragmentPerfilBinding.inflate(inflater, container, false)
        var nome_fragment = binding.nomePerfilAutonomoFrag
        var cpf_fragment = binding.cpfPerfilAutonomoPerfil
        var email_fragment = binding.emailPerfilAutonomoFragment
        var telefone_fragment = binding.telefonePerfilAutonomoFrag
        var senha_fragment = binding.senhaPerfilAutonomo
        var categoria_campo = binding.categoriaPerfilAutonomo
        var dataDeNascimento = binding.txtDataDeNascimentoPerfil
        var descricaoFragmentPerfil = binding.descricaoServicoPerfil
        var valorPHora = binding.valorHora
        var foto_perfil = binding.fotoPerfilFrag

        decoder(Identificacao.foto, foto_perfil)

        var editar_botao_fragment = binding.bttEditarPerfilFrag
        var tamanhoSenha = Identificacao.senha.length
        val parser = SimpleDateFormat("yyyy-MM-dd")
        val formatter = SimpleDateFormat("dd.MM.yyyy")
        val formattedDate = formatter.format(parser.parse(Identificacao.datanasc))

        println("--------------------------Dentro")
        nome_fragment.setText("${Identificacao.nome}")
        cpf_fragment.setText("CPF: ${Identificacao.cpf}")
        email_fragment.setText("Email: ${Identificacao.email}")
        telefone_fragment.setText("Telefone: ${Identificacao.telefone}")
        senha_fragment.setText("Senha: ${"*".repeat(tamanhoSenha)}")
        categoria_campo.setText("Categoria: ${Identificacao.categoria}")
        dataDeNascimento.setText("Data de Nascimento: ${formattedDate}")
        descricaoFragmentPerfil.setText("Descrição: ${Identificacao.descricao}")
        valorPHora.setText("Valor por Hora: R$${Identificacao.preco} ")
        val exc = binding.bttExcluirContaPerfil




        exc.setOnClickListener() {

            val scope = CoroutineScope(Job())
            scope.launch {
                Biccorequests.deletar(
                    "https://bicco-api.herokuapp.com/deletar/autonomo",
                    Identificacao.identificacao
                )
            }

            var intent = Intent(context, FormLogin::class.java)
            startActivity(intent)


        }




        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
    }


    @RequiresApi(Build.VERSION_CODES.O)
    fun decoder(fotoemString: String, imagem: ImageView) {
        var imageBytes: ByteArray = fotoemString.toByteArray()
        imageBytes = Base64.decode(imageBytes, DEFAULT)
        var decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
        imagem.setImageBitmap(decodedImage)
//    }
    }

    fun janelaModal() {
        val alert = androidx.appcompat.app.AlertDialog.Builder(this.requireContext())
        val SenhadeRecuperacao = gerador.getRandomPass()
        alert.setTitle("Excluir conta")
        alert.setMessage("Tem certeza que deseja excluir sua conta");
        alert.setPositiveButton("Já anotei meu número de recuperação de senha") { dialog, whichButton ->
            var intent = Intent(this.context, Apresentacao::class.java)
            startActivity(intent)
        }







    }
}
