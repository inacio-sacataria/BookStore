package com.decode.bookstore_admin.data

import android.content.Context
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

object FirebaseInstance {

    @Volatile
    var INSTANCE : FirebaseDatabase? = null

     fun getInstance(): FirebaseDatabase {

        if (INSTANCE==null){
            INSTANCE = FirebaseDatabase.getInstance()
        }
        return INSTANCE!!
    }
}



