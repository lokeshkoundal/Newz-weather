package com.example.newz.newz

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newz.newz.models.NewsModel
import com.example.newz.newz.network.NewsApiService
import com.example.newz.newz.network.RetrofitObj
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class NewsVM : ViewModel() {
    private val newsApiService = RetrofitObj.api.create(NewsApiService::class.java)

    val articles = MutableLiveData<NewsModel>()

    @SuppressLint("NullSafeMutableLiveData")
    suspend fun getTopHeadlines(country: String) {
        val data: NewsModel? = newsApiService.getTopHeadlines(country).body()
        if (data != null) {
            articles.value = data
        }

    }
}