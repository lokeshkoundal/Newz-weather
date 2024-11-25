package com.example.newz.db

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class NewsVmDb(application: Application) : AndroidViewModel(application){
    var allNews: LiveData<List<News>> = MutableLiveData()
    private val db = NewsDB.getDatabase(application)

    private val newsDao: NewsDao = db.newsDao()
     fun insertBookmarkedNews(news: News){
        newsDao.insert(news)
    }

     fun getAllBookmarkedNews(){
        allNews = newsDao.getAllNews()
    }

     fun deleteBookmarkedNews(newsId: Int){
        newsDao.deleteNews(newsId)
    }

    fun deleteBookmarkedNewsByTitle(title: String){
        newsDao.deleteNews(title)

    }


}