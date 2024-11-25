package com.example.newz.newz

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newz.newz.models.NewsModel
import com.example.newz.newz.network.NewsApiService
import com.example.newz.newz.network.RetrofitObj

class NewsVM : ViewModel() {
    private val newsApiService = RetrofitObj.api.create(NewsApiService::class.java)
    val articles = MutableLiveData<NewsModel>()
    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> = _isLoading


    @SuppressLint("NullSafeMutableLiveData")
    suspend fun getTopHeadlines(country: String, category:String) {
        _isLoading.value = true

        try {
            val data: NewsModel? = newsApiService.getTopHeadlines(country,category).body()
            if (data != null) {
                articles.value = data
            }
        }catch (e :Exception){
            Log.d("NewsVM",e.toString())
        }finally {
            _isLoading.value = false
        }

        _isLoading.value = false

    }

}