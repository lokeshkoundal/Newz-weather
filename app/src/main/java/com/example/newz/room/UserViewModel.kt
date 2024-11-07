package com.example.newz.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UserViewModel(application: Application) : AndroidViewModel(application){
    private lateinit var allData: LiveData<List<User>>
    private val repository : UserRepository
    init {
         val userDao = UserDatabase.getDatabase(application).userDao()
        repository = UserRepository(userDao)
//        viewModelScope.launch {
//            allData = repository.readAllData()
//        }
    }

    fun addUser(user: User){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }


}