package com.decode.bookstore_admin.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.decode.bookstore_admin.R
import com.decode.bookstore_admin.databinding.ActivityRegistCategoryBinding
import com.decode.bookstore_admin.ui.viemodel.CategoriaViewModel
import com.decode.bookstore_admin.ui.viemodel.LivrosViewModel

class RegistCategoryActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegistCategoryBinding
    lateinit var viewModel: CategoriaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(CategoriaViewModel::class.java)
        binding.btnAddCategory.setOnClickListener {
            verifyNullable()
        }
    }
    fun verifyNullable(){
        if (!binding.edtCategoryName.text.isNullOrEmpty()){
            addCategoria()
        }
    }

    private fun addCategoria() {
        var name = binding.edtCategoryName.text.toString()
        var descricao : String = if(binding.edtCategoryDescription.text.isBlank()) "" else binding.edtCategoryDescription.text.toString()
        viewModel.addCategory(name,descricao)
        finish()
    }
}