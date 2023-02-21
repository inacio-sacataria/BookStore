package com.decode.bookstore_admin.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.decode.bookstore_admin.R
import com.decode.bookstore_admin.data.entities.Books
import com.decode.bookstore_admin.databinding.ActivityDetailsBinding
import com.decode.bookstore_admin.ui.viemodel.LivrosViewModel
import com.decode.bookstore_admin.utils.OBJECT_CLIKED
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.database.FirebaseDatabase

class DetailsActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailsBinding
    lateinit var viewModel: LivrosViewModel
    var books: Books? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(LivrosViewModel::class.java)


        BottomSheetBehavior.from(binding.standardBottomSheet).apply {
            peekHeight=600
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }

        filldata()

        binding.btnEdit.setOnClickListener {
            var intent = Intent(this, RegistBookActivity::class.java)
            intent.putExtra("books", OBJECT_CLIKED.value )
            startActivity(intent)
        }

        binding.btnDelete.setOnClickListener {

            books?.id?.let { id ->
                dialogShow(id)
            }

        }

        binding.btnBack.setOnClickListener {
            onBackPressed()
        }
    }

    private fun filldata() {

            OBJECT_CLIKED?.observeForever{
            books = it
            binding.tvTittle.text = it.title
            binding.tvQuant.text = it.quantidade.toString()
            binding.tvCategory.text = it.categoria
            binding.tvAuthor.text = it.autor
            binding.tvYear.text = it.ano.toString()
            binding.tvSinopse.text = it.sinopse

            Glide
                .with(applicationContext)
                .load(it.cover)
                .centerCrop()
                .placeholder(R.drawable.cover)
                .into(binding.imgCover);
        }
    }


    fun dialogShow(id : String){
        MaterialAlertDialogBuilder(this)
            .setTitle(("Deseja apgar este livro?"))
            .setMessage(("Apagando o livro , significa que nao vai poder recupera-lo caso precise novamente"))
            .setPositiveButton("Apagar") { dialog, which ->
                viewModel.delete(id)
                finish()
            }
            .setNegativeButton("Cancelar") { dialog, which ->


            }.setCancelable(false)
            .show()
    }

    override fun onResume() {
        super.onResume()

    }
}