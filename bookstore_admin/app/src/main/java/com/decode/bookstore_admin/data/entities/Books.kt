package com.decode.bookstore_admin.data.entities

import android.icu.text.CaseMap.Title
import android.os.Parcelable
import com.google.firebase.database.Exclude
import kotlinx.android.parcel.Parcelize
import java.util.UUID

@Parcelize
data class Books(
    var id : String?="",
    var title: String?="",
    var sinopse : String?="",
    var cover : String?= "",
    var ano : Int ?= 0,
    var autor : String?="",
    var editora : String?="",
    var categoria : String?="",
    var quantidade : Int?=0
): Parcelable {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "id" to id,
            "title" to title,
            "cover" to cover,
            "ano" to ano,
            "sinopse" to sinopse,
            "autor" to autor,
            "editora" to editora,
            "categoria" to categoria,
            "quantidade" to quantidade
        )
    }
}
