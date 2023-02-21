package com.decode.bookstore_admin.data.entities

import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.parcelize.Parcelize
import java.util.UUID

@Parcelize
data class Categoria(
    var id : String? = "",
    var name : String? ="",
    var descricao : String?="sem descrição"
): Parcelable{
    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "name" to name,
            "descricao" to descricao
        )
    }
}