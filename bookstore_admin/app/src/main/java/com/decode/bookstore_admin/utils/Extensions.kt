package com.decode.bookstore_admin.utils

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.compose.ui.graphics.drawscope.Stroke

fun View.visibility(boolean: Boolean){
    if (boolean){
        this.visibility = View.VISIBLE
    }else{
        this.visibility = View.GONE
    }
}

fun Context.showInfo(text: String){
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}