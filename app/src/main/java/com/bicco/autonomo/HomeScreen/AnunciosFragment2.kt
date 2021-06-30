package com.bicco.autonomo.HomeScreen

import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bicco.autonomo.Form.Login.Biccorequests
import com.bicco.autonomo.R
import com.bicco.autonomo.databinding.FragmentAnuncios2Binding
import com.bicco.autonomo.databinding.FragmentPerfilBinding
import java.io.ByteArrayOutputStream

class AnunciosFragment2: Fragment() {
    private var SelecionarUri: Uri? = null
    lateinit var imageString : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity).supportActionBar?.title = "Perfil"
        val binding = FragmentAnuncios2Binding.inflate(inflater, container, false)
        val foto_perfil1 = binding.fotoPortifolio1
        val foto_perfil2 = binding.fotoPortifolio2
        val foto_perfil3 = binding.fotoPortifolio3
        val foto_perfil4 = binding.fotoPortifolio4
        val botao_excluir1 = binding.excluir1
        val botao_excluir2 = binding.excluir2
        val botao_excluir3 = binding.excluir3
        val botao_excluir4 = binding.excluir4

        botao_excluir1.setOnClickListener {

        }
        botao_excluir2.setOnClickListener {

        }
        botao_excluir3.setOnClickListener {

        }
        botao_excluir4.setOnClickListener {

        }
        foto_perfil1.setOnClickListener(){
//            if()
////            selecionarFotoDaGaleria()
//            var situacao = Biccorequests.adicionarFoto("https://bicco-api.herokuapp.com/portfolio/adicionar",Identificacao.identificacao, "a2b1")
        }
        foto_perfil2.setOnClickListener(){
//            selecionarFotoDaGaleria()

        }
        foto_perfil3.setOnClickListener(){
//            selecionarFotoDaGaleria()

        }
        foto_perfil4.setOnClickListener(){
//            selecionarFotoDaGaleria()

        }


        return binding.root
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?, image: ImageView) {
//        val binding = FragmentAnuncios2Binding.inflate(layoutInflater)
//        var viewportifolio = image
//
//        if (requestCode == 0) {
//            SelecionarUri = data?.data
//            val bitmap = MediaStore.Images.Media.getBitmap(ContextWrapper(context).contentResolver, SelecionarUri)
//            viewportifolio.setImageBitmap(bitmap)
//
//            val baos = ByteArrayOutputStream()
//            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
//            var imageBytes: ByteArray = baos.toByteArray()
//            imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT)
//            Log.d("CODED", "$imageString")
//
//
//        }
        fun selecionarFotoDaGaleria() {

            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            startActivityForResult(intent, 0)
        }



}
//}