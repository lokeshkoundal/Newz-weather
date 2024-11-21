package com.example.newz.db

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsVmDb(private val application: Application) : AndroidViewModel(application){
    private lateinit var allNews: LiveData<List<News>>
    private val db = NewsDB.getDatabase(application)

    suspend fun insertBookmarkedNews(news: News){
        db.newsDao().insert(news)
    }

    suspend fun getAllBookmarkedNews(){
        allNews = db.newsDao().getAllNews()
    }

    suspend fun deleteBookmarkedNews(newsId: Int){
        db.newsDao().deleteNews(newsId)
    }


}