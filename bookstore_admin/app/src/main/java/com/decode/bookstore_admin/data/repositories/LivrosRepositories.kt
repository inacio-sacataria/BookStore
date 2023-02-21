package com.decode.bookstore_admin.data.repositories

import android.util.Log
import android.widget.Toast
import com.decode.bookstore_admin.data.FirebaseInstance
import com.decode.bookstore_admin.data.entities.Books
import com.decode.bookstore_admin.utils.BOOKS_PATH
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await
import java.util.*

class LivrosRepositories() {

    val db = FirebaseInstance.getInstance().reference
    val myRef = db.child(BOOKS_PATH)


     suspend fun createBook(books: Books): Boolean {
        var books = Books(
            id = UUID.randomUUID().toString(),
            title = books.title,
            sinopse = books.sinopse,
            cover = books.cover,
            ano = books.ano,
            autor = books.autor,
            editora = books.editora,
            categoria = books.categoria,
            quantidade = books.quantidade
        )

         var success = false
        myRef.child(books.id!!).setValue(books)
            .addOnSuccessListener {
                 success = true
            }.addOnFailureListener {
                success = false
                Log.d(TAG, "Failed on add : ${it.message}")
            }.await()

         return success
    }

     suspend fun getBooks(): MutableList<Books>{
        var mutableList = mutableListOf<Books>()
         myRef.get().addOnSuccessListener {
             for ( snap in it.children) {
                 var books = snap.getValue(Books::class.java)
                 Log.d("Livros", "${books!!.title}")
                 mutableList.add(books!!)
             }
         }.addOnFailureListener {
             Log.d(TAG,"Error on get all books ${it.message}")
         }.await()

        return mutableList
    }

    suspend fun getUniqueBooks(id:String): Books?{
        lateinit var books: Books
        myRef.child(id).get().addOnSuccessListener {
                var books = it.getValue(Books::class.java)
                Log.d("Livros", "${books!!.title}")
               books = books!!
        }.addOnFailureListener {
            Log.d(TAG,"Error on get all books ${it.message}")
        }.await()

        return return books
    }

    suspend fun update(books: Books){
        books.id?.let {
            myRef.child(it).updateChildren(books.toMap()).await()
        }
    }

    suspend fun delete(id: String){
        id?.let {
            myRef.child(it).removeValue().await()
        }
    }

    suspend fun updateQuantidade(books: Books, quant : Int){
        var quantAvalaible  = books.quantidade?.minus(quant)
        books.quantidade = quantAvalaible

        books.id?.let { myRef.child(it).updateChildren(books.toMap()) }
    }

    companion object{
        val TAG= "Livros"
    }
}