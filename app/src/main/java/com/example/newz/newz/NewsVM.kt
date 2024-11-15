package com.example.newz.newz

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newz.db.NewsDB
import com.example.newz.newz.models.NewsModel
import com.example.newz.newz.network.NewsApiService
import com.example.newz.newz.network.RetrofitObj
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsVM : ViewModel() {
    private val newsApiService = RetrofitObj.api.create(NewsApiService::class.java)
    private lateinit var _articles : NewsModel
    val articles = MutableLiveData<NewsModel>()
    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = _isLoading

    val bookmarkedNews = MutableLiveData<NewsModel>()

    @SuppressLint("NullSafeMutableLiveData")
    suspend fun getTopHeadlines(country: String, category:String) {
        _isLoading.value = true

        try {
            val data: NewsModel? = newsApiService.getTopHeadlines(country,category).body()
            if (data != null) {
                articles.value = data
            }
        }catch (e :Exception){
            Log.d("tagg","Excepttioonnn aaa gayiii")
        }finally {
            _isLoading.value = false
        }

        _isLoading.value = false

    }

}