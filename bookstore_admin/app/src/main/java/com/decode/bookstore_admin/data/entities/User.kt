package com.decode.bookstore_admin.data.entities

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User(
    val id:String?= "" ,
    var nome:String? = "",
    var email: String? = "",
    var contacto: String? = "",
    var instituicao: String? = "",
    var endereco : String?="",
    var photoUrl: String?="",
) : Parcelable {

}