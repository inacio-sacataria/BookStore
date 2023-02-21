package com.decode.bookstore_admin.ui.viemodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.decode.bookstore_admin.data.entities.Books
import com.decode.bookstore_admin.data.entities.UsersRequest
import com.decode.bookstore_admin.data.repositories.RequestRepositories
import kotlinx.coroutines.launch

class RequestViewModel: ViewModel() {
    var repositories = RequestRepositories()

    var livros = MutableLiveData<MutableList<Books>>()


    var request = MutableLiveData<MutableList<UsersRequest>>()


    init {
         getRequest()
    }



    fun getRequest(){
        viewModelScope.launch {
            var mutableList = repositories.getRequest()
            request.postValue(mutableList)
        }
    }

}