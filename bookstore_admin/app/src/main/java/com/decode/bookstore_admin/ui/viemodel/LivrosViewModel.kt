package com.decode.bookstore_admin.ui.viemodel

import android.content.Context
import android.util.Log
import android.widget.ArrayAdapter
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decode.bookstore_admin.data.entities.Books
import com.decode.bookstore_admin.data.entities.Categoria
import com.decode.bookstore_admin.data.repositories.CategoriaRepositories
import com.decode.bookstore_admin.data.repositories.LivrosRepositories
import com.decode.bookstore_admin.utils.OBJECT_CLIKED
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LivrosViewModel: ViewModel() {
    var repositories = LivrosRepositories()
    var categoriaRepositories = CategoriaRepositories()
    var livros = MutableLiveData<MutableList<Books>>()
    var categorias = MutableLiveData<MutableList<Categoria>>()

    init {
        getBooks()
        getArrayAdapter()
    }

    fun addBooks(  title: String,
                   sinopse : String,
                   cover : String,
                   ano : Int,
                   autor : String,
                   editora : String,
                   categoria : String,
                   quantidade : Int){

        viewModelScope.launch(Dispatchers.IO){
            var books = Books(
                title=title,
                sinopse = sinopse,
                cover = cover,
                ano = ano,
                autor = autor,
                editora = editora,
                categoria = categoria,
                quantidade = quantidade
            )

            repositories.createBook(books)
        }
    }

    fun updateBooks( id : String,
                     title: String,
                     sinopse : String,
                     cover : String,
                     ano : Int,
                     autor : String,
                     editora : String,
                     categoria : String,
                     quantidade : Int){
        viewModelScope.launch {
            var books = Books(
                id = id,
                title=title,
                sinopse = sinopse,
                cover = cover,
                ano = ano,
                autor = autor,
                editora = editora,
                categoria = categoria,
                quantidade = quantidade
            )
            OBJECT_CLIKED.postValue(books)
            repositories.update(books)
        }
    }

    fun getBooks(){
        viewModelScope.launch {
            var mutableList = repositories.getBooks()
            livros.postValue(mutableList)
            for (books in mutableList){
                Log.d(LivrosRepositories.TAG,"view model ${books.title}")
            }
        }
    }

    fun delete(id: String){
        viewModelScope.launch {
            repositories.delete(id)
        }
    }


    fun getArrayAdapter(){
       viewModelScope.launch {
           categorias.value = categoriaRepositories.getCategoria()
       }
    }
}