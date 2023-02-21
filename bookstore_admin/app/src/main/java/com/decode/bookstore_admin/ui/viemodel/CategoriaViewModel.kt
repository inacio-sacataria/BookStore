package com.decode.bookstore_admin.ui.viemodel

import android.R
import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.decode.bookstore_admin.data.entities.Books
import com.decode.bookstore_admin.data.entities.Categoria
import com.decode.bookstore_admin.data.repositories.CategoriaRepositories
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriaViewModel : ViewModel(){
    var repositories= CategoriaRepositories()
    var categorias = MutableLiveData<MutableList<Categoria>>()

    init {
        getCategoria()
    }
    fun addCategory(name: String,descricao: String){
        viewModelScope.launch(Dispatchers.IO){
            var category = Categoria(name = name, descricao = descricao)
            repositories.createCategories(category)
        }
    }

    fun getCategoria(){
        viewModelScope.launch{
            var mutableList = repositories.getCategoria()
            categorias.postValue(mutableList)
            for (categoria  in mutableList){
                Log.d(CategoriaRepositories.TAG, "viewmodel categories ${categoria.name}")
            }
        }
    }


}