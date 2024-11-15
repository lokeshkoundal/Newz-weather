package com.example.newz.db

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsVmDb(private val context: Application) : ViewModel() {
    private lateinit var news: MutableLiveData<List<News>>

//    suspend fun getBookmarkedNews(){
//        val db = NewsDB.getDatabase(context)
//        db.newsDao().insert(news)
//    }


}