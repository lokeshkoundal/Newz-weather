package com.example.newz.room

import androidx.lifecycle.LiveData

class UserRepository(private val userDao: UserDao) {


    suspend fun readAllData() : LiveData<List<User>>{
       return userDao.readAllData()
    }

    suspend fun addUser(user: User){
        userDao.addUser(user)

    }
}