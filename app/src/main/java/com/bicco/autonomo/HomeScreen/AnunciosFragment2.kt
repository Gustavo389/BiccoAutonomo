package com.bicco.autonomo.HomeScreen

import android.content.ContextWrapper
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Base64
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bicco.autonomo.Form.Login.Biccorequests
import com.bicco.autonomo.R
import com.bicco.autonomo.databinding.FragmentAnuncios2Binding
import com.squareup.picasso.Picasso
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.Item
import com.xwray.groupie.ViewHolder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream


class AnunciosFragment2 : Fragment() {
    private var fragment: FragmentAnuncios2Binding? = null
    private var SelecionarUri: Uri? = null
    private var imageString: String? = null
    private lateinit var Adapter: GroupAdapter<ViewHolder>
    lateinit var fotoemString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        (activity as AppCompatActivity).supportActionBar?.title = "Portfólio"
        return inflater.inflate(R.layout.fragment_anuncios2, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bindingp = FragmentAnuncios2Binding.bind(view)
        var recyclerViewAnuncios = bindingp.recyclerViewAnuncios as RecyclerView
        fragment = bindingp
        Adapter = GroupAdapter()
        recyclerViewAnuncios.setLayoutManager(LinearLayoutManager(context));
        recyclerViewAnuncios.adapter = Adapter

//        val scope = CoroutineScope(Job())
//        scope.launch {
//            var list = Biccorequests.verTodosPortfolio(
//                "https://bicco-api.herokuapp.com/portfolio/ver",
//                Identificacao.identificacao
//            )
//
//            for (foto in list) {
//                println("FOTO: " + foto)
//                //aqui estao as fotos
//
//            }
//        }
//        var floatbutton = bindingp.floatingActionButton
//        floatbutton.setOnClickListener() {
//
//            scope.launch {
//                val fotosCadastradas = Biccorequests.numeroDeFotos(
//                    "https://bicco-api.herokuapp.com/portfolio/contar",
//                    Identificacao.identificacao
//                ); // sem thread
//                if (4 - fotosCadastradas > 0) {
//                    // codigo que libera o botao
//                    selecionarFotoDaGaleria()
//
//                } else {
//
//                    // toast de limite atingido, exclua uma imagem ou mais imagens...
//                }
//            }
//        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var scope = CoroutineScope(Job())
        scope.launch{
        val binding = FragmentAnuncios2Binding.inflate(layoutInflater)
        if (requestCode == 0) {
            println("Entrou na activity")
            SelecionarUri = data?.data
            val bitmap = MediaStore.Images.Media.getBitmap(
                ContextWrapper(context).contentResolver,
                SelecionarUri
            )
            val baos = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
            var imageBytes: ByteArray = baos.toByteArray()
            imageString = Base64.encodeToString(imageBytes, Base64.DEFAULT)
            println("Após encode")
            var situacao = Biccorequests.adicionarFoto("https://bicco-api.herokuapp.com/portfolio/adicionar", Identificacao.identificacao,imageString)
            if(situacao == "sucesso") {
                println("Imagem cadastrada")

            }
            if(situacao == "error"){
                println("falha")
            }
        }

    }
    }
    fun selecionarFotoDaGaleria() {

        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, 0)
    }


    private inner class fotosItens : Item<ViewHolder>() {

        override fun getLayout(): Int {
            return R.layout.lista
        }

        override fun bind(viewHolder: ViewHolder, position: Int) {
            println("Dentro do Bind e rodando query")

            var lista = Biccorequests.verTodosPortfolio("https://bicco-api.herokuapp.com/portfolio/ver", Identificacao.identificacao);

            var imageBytes: ByteArray = fotoemString.toByteArray()
            imageBytes = Base64.decode(imageBytes, Base64.DEFAULT)
            var decodedImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            viewHolder.itemView.findViewById<Button>(R.id.excluir)
            println(lista)
            var fotoPort = viewHolder.itemView.findViewById<ImageView>(R.id.foto_portifolio)

            Picasso.get().load("${fotoPort.setImageBitmap(decodedImage)}").into(fotoPort)


        }


    }


}

