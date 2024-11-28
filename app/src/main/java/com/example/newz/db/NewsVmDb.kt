package com.example.newz.db


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsVmDb @Inject constructor(private val newsDao: NewsDao):ViewModel(){
    var allNews: LiveData<List<News>> = MutableLiveData()
//    private val db = NewsDB.getDatabase(application)

//    private val newsDao: NewsDao = db.newsDao()
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