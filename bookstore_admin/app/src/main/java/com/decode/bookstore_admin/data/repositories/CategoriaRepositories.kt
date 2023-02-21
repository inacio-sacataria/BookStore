package com.decode.bookstore_admin.data.repositories

import android.util.Log
import com.decode.bookstore_admin.data.FirebaseInstance
import com.decode.bookstore_admin.data.entities.Books
import com.decode.bookstore_admin.data.entities.Categoria
import com.decode.bookstore_admin.utils.CATEGORIES_PATH
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await
import java.util.*

class CategoriaRepositories() {
    var db = FirebaseInstance.getInstance().reference
    var myRef = db.child(CATEGORIES_PATH)

    suspend fun createCategories(categ: Categoria){
        var categoria = Categoria (
            id= UUID.randomUUID().toString(),
            name = categ.name,
            descricao = categ.descricao
                )
        var isSuccessAdded = false
        myRef.child(categoria.id!!).setValue(categoria).addOnSuccessListener {
            isSuccessAdded = true
        }.addOnFailureListener {
            isSuccessAdded = false
            Log.d(TAG,"Error adding categoria ${it.message}")
        }.await()
    }

    suspend fun getCategoria(): MutableList<Categoria>{
        var mutableList = mutableListOf<Categoria>()
        myRef.get().addOnSuccessListener {
            for ( snap in it.children) {
                var categoria = snap.getValue(Categoria::class.java)
                Log.d(TAG, "${categoria!!.name}")
                mutableList.add(categoria!!)
            }
        }.addOnFailureListener {
            Log.d(TAG,"Error on get all categoria ${it.message}")
        }.await()

        return mutableList
    }

    suspend fun update(categ: Categoria){
        categ.id?.let { myRef.child(it).updateChildren(categ.toMap()) }
    }
    companion object {
        val TAG = "categoria"
    }
}