package com.decode.bookstore_admin.data.repositories

import android.util.Log
import com.decode.bookstore_admin.data.FirebaseInstance
import com.decode.bookstore_admin.data.entities.UsersRequest
import com.decode.bookstore_admin.utils.REQUEST_PATH
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.tasks.await
import java.util.*


class RequestRepositories () {
    val db = FirebaseInstance.getInstance().reference
    val myRef = db.child(REQUEST_PATH)

    suspend fun createBook(usersRequest: UsersRequest): Boolean {
        var request = UsersRequest(
            id = UUID.randomUUID().toString(),
            books = usersRequest.books,
            readers = usersRequest.readers,
            acceted = usersRequest.acceted,
            entregueAoLeitor = usersRequest.entregueAoLeitor,
            devolvido = usersRequest.devolvido,
        )

        var success = false
        myRef.child(usersRequest.id!!).setValue(request)
            .addOnSuccessListener {
                success = true
            }.addOnFailureListener {
                success = false
                Log.d(TAG, "Failed on add : ${it.message}")
            }.await()

        return success
    }

    suspend fun getRequest(): MutableList<UsersRequest>{
        var mutableList = mutableListOf<UsersRequest>()
        myRef.get().addOnSuccessListener {
            for ( snap in it.children) {
                var request = snap.getValue(UsersRequest::class.java)
                Log.d("Livros", "${request!!.books}")
                mutableList.add(request!!)
            }
        }.addOnFailureListener {
            Log.d(TAG,"Error on get all books ${it.message}")
        }.await()

        return mutableList
    }


     fun update(usersRequest: UsersRequest){
        usersRequest.id?.let {
            myRef.child(it).updateChildren(usersRequest.toMap())
        }
    }


     fun remove(usersRequest: UsersRequest){
        usersRequest.id?.let {
            myRef.child(it).removeValue()
        }
    }

    companion object{
        val TAG= "Request"
    }
}