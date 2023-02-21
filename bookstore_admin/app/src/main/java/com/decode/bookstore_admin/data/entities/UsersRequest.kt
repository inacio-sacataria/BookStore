package com.decode.bookstore_admin.data.entities

import com.google.firebase.database.Exclude

class UsersRequest(
    var id : String? = "",
    var books: Books? = null,
    var readers: User? = null,
    var acceted: Boolean? = false,
    var entregueAoLeitor : Boolean?= false,
    var devolvido : Boolean?=false,
) {
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "books" to books,
            "readers" to readers,
            "isAcceted" to acceted,
            "isEntregueAoLeitor" to entregueAoLeitor,
            "isDevolvido" to devolvido,
        )
    }
}

