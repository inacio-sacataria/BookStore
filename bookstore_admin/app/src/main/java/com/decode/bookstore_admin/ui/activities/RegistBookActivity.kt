package com.decode.bookstore_admin.ui.activities

import android.app.Activity
import android.app.ProgressDialog
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.decode.bookstore_admin.R
import com.decode.bookstore_admin.data.entities.Books
import com.decode.bookstore_admin.data.repositories.LivrosRepositories
import com.decode.bookstore_admin.databinding.ActivityRegistBookBinding
import com.decode.bookstore_admin.ui.viemodel.LivrosViewModel
import com.decode.bookstore_admin.utils.populate
import com.decode.bookstore_admin.utils.showInfo
import com.decode.bookstore_admin.utils.visibility
import com.google.firebase.storage.FirebaseStorage
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

class RegistBookActivity : AppCompatActivity() {

    lateinit var binding: ActivityRegistBookBinding
    lateinit var viewModel: LivrosViewModel
    var page = 1
    var coverUrl : String = ""
    var isUpdate = false
    var books : Books? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityRegistBookBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(LivrosViewModel::class.java)

        viewModel.categorias.observe(this) {
            // var array = ArrayList()
            var array : MutableList<String> = ArrayList()
            it.forEach {
                array.add(it.name!!)
            }
            binding.spinnerCategoria.populate(this,array)
        }


        binding.spinnerCategoria.onItemSelectedListener = object :
            AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>,
                                        view: View, position: Int, id: Long) {
//                Toast.makeText(this@RegistBookActivity,
//                    categorias[position].toString(), Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // write code to perform some action
            }
        }

        binding.btnAddLivro.setOnClickListener {
            viewProeed()
        }

        binding.btnChooseCover.setOnClickListener {
            selectImage()
        }

        books = intent.getParcelableExtra<Books>("books")
        books?.let { fillfields(it) }
    }

    private fun fillfields(books: Books) {
       if (books!=null){
           isUpdate = true
           binding.edtTitulo.setText(books.title)
           binding.edtSinopse.setText(books.sinopse)
           binding.edtAuthor.setText(books.autor)
           binding.edtEditora.setText(books.editora)
           binding.edtAno.setText(books.ano.toString())
           binding.edtQuantidade.setText(books.quantidade.toString())

           if (bitmap==null){
               Glide
                   .with(this)
                   .load(books.cover)
                   .placeholder(R.drawable.cover)
                   .into(binding.imgCover)
               coverUrl = books.cover.toString()
           }else{
               binding.imgCover.setImageBitmap(bitmap)
           }

       }
    }


    fun viewProeed(){
       if(!binding.edtTitulo.text.isNullOrEmpty() && !binding.edtSinopse.text.isNullOrEmpty() && page==1){
           page=2
           proceedViewLogic(page)
       }else if (!binding.edtAuthor.text.isNullOrEmpty() && !binding.edtEditora.text.isNullOrEmpty() && !binding.edtAno.text.isNullOrEmpty() && page==2){
           page=3
           proceedViewLogic(page)
       }else if (!binding.edtQuantidade.text.isNullOrEmpty() && page==3) {
           page=4
           proceedViewLogic(page)
       }else if (page==4){
            if (!coverUrl.isNullOrEmpty()){
                addLivros()
            }else{
                this.showInfo("Faca o upload da cover")
            }
        }
    }
    
    fun proceedViewLogic(number: Int){
        when(number){
            1 -> {
                binding.lytThirdOne.visibility(true)
                binding.lytSecondOne.visibility(false)
                binding.lytThirdOne.visibility(false)
                binding.lytFourthyOne.visibility(false)
            }
            2 ->{
                binding.lytFirstOne.visibility(false)
                binding.lytSecondOne.visibility(true)
                binding.lytThirdOne.visibility(false)
                binding.lytFourthyOne.visibility(false)
            }
            3 -> {
                binding.lytThirdOne.visibility(false)
                binding.lytSecondOne.visibility(false)
                binding.lytThirdOne.visibility(true)
                binding.lytFourthyOne.visibility(false)
            }
            4 -> {
                binding.lytThirdOne.visibility(false)
                binding.lytSecondOne.visibility(false)
                binding.lytThirdOne.visibility(false)
                binding.lytFourthyOne.visibility(true)
                binding.btnAddLivro.setText("Adicionar Livro")
            }
            }
        }

    fun addLivros(){
        if( !binding.edtTitulo.text.isNullOrEmpty() && !binding.edtSinopse.text.isNullOrEmpty() &&
            !binding.edtAuthor.text.isNullOrEmpty() && !binding.edtEditora.text.isNullOrEmpty() &&
            !binding.edtQuantidade.text.isNullOrEmpty() && !binding.edtAno.text.isNullOrEmpty()){

            var title = binding.edtTitulo.text.toString()
            var sinopse = binding.edtSinopse.text.toString()
            var cover = coverUrl
            var ano = binding.edtAno.text.toString().toInt()
            var autor = binding.edtAuthor.text.toString()
            var editora = binding.edtEditora.text.toString()

            var category : String = binding.spinnerCategoria.selectedItem.toString()
            var quantidade = binding.edtQuantidade.text.toString().toInt()

            if (books==null)
                viewModel.addBooks(title , sinopse ,cover,ano, autor , editora , category,quantidade)
            else
                books!!.id?.let {
                    viewModel.updateBooks(it, title , sinopse ,cover,ano, autor , editora , category, quantidade)
                }
            finish()
        }else{
            this.showInfo("Por favor preencha todos dados")
        }
    }


    public fun selectImage(){
        try {
            val intent= Intent()
            intent.setType("image/*")
            intent.setAction( Intent.ACTION_GET_CONTENT)

            startActivityForResult(
                Intent.createChooser(
                    intent,
                    "Select Image from here..."),
                REQUEST_CODE);

        }catch (e: Exception){
            Log.e("upload"," upload ${e.message.toString()}")
        }
    }

    private fun uploadImage(imageURI: Uri){
        val progressDialog= ProgressDialog(this)
        progressDialog.setMessage("Upload cover....")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val  formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now= Date()
        val filename= formatter.format(now)
        val cover ="images/$filename"
        val storageRef= FirebaseStorage.getInstance().getReference(cover)
        storageRef.putFile(imageURI).addOnSuccessListener {

            storageRef.downloadUrl.addOnSuccessListener {
                coverUrl = it.toString()
                if (progressDialog.isShowing) progressDialog.dismiss()
            }.addOnFailureListener {
                this.showInfo("Failed to download")
            }

        }.addOnFailureListener {
            if (progressDialog.isShowing) progressDialog.dismiss()
            Toast.makeText(this, "failed upload", Toast.LENGTH_SHORT).show()
        }
    }

    fun uploadtoFirebase(bb: ByteArray){
        val progressDialog= ProgressDialog(this)
        progressDialog.setMessage("Scaning your User License....")
        progressDialog.setCancelable(false)
        progressDialog.show()

        val  formatter = SimpleDateFormat("yyyy_MM_dd_HH_mm_ss", Locale.getDefault())
        val now= Date()
        val filename= formatter.format(now)
        val storageRef= FirebaseStorage.getInstance().getReference("images/$filename")
        storageRef.putBytes(bb).addOnSuccessListener {

            if (it.task.isSuccessful){
                storageRef.downloadUrl.addOnSuccessListener {
                   coverUrl = it.toString()
                    Toast.makeText(this, "complete upload", Toast.LENGTH_SHORT).show()
                    if (progressDialog.isShowing) progressDialog.dismiss()
                }.addOnFailureListener {
                    this.showInfo("Failed to download")
                }

            }



            //binding.imageChose.setImageURI(imageURI)
        }.addOnFailureListener {
            if (progressDialog.isShowing) progressDialog.dismiss()
            this.showInfo("Failed to Upload")
        }
    }

    var bitmap: Bitmap? = null
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data != null)
        {
            var imageURI= data?.data
            try {
                bitmap = MediaStore.Images.Media.getBitmap(contentResolver,imageURI);
                binding.imgCover.setImageBitmap(bitmap)
                if (imageURI != null) {
                    uploadImage(imageURI)
                }
            }catch (e:IOException){
                Log.d(LivrosRepositories.TAG, "photo not uploaded ${e.message}")
            }

        }else{
            Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
        }
    }


    override fun onBackPressed() {
        if (page == 1) {
            super.onBackPressed()
        } else {
            page--
            proceedViewLogic(page)
        }
    }


    companion object{
        val REQUEST_CODE = 100
    }
}
