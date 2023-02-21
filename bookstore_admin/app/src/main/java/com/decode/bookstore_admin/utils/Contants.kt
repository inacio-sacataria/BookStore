package com.decode.bookstore_admin.utils

import androidx.lifecycle.MutableLiveData
import com.decode.bookstore_admin.data.entities.Books

val BOOKS_PATH = "books"
val REQUEST_PATH = "requests"
val AUTHOR_PATH = "author"
val CATEGORIES_PATH = "categoria"
var OBJECT_CLIKED =MutableLiveData<Books>()
var ID_CLIKED = ""