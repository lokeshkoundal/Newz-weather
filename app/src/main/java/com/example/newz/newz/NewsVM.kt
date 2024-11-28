package com.example.newz.newz

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newz.newz.models.NewsModel
import com.example.newz.newz.network.NewsApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NewsVM @Inject constructor(private val newsApiService: NewsApiService): ViewModel() {

//    private val newsApiService = RetrofitObj.api.create(NewsApiService::class.java)
    val articles = MutableLiveData<NewsModel>()
    private val _isLoading = MutableLiveData(true)
    val isLoading: LiveData<Boolean> = _isLoading
    var currentCategory = MutableLiveData<String>()

    @SuppressLint("NullSafeMutableLiveData")
    suspend fun getTopHeadlines(country: String) {
        _isLoading.value = true

        try {
            val data: NewsModel? =
                currentCategory.value?.let { newsApiService.getTopHeadlines(country, it).body() }
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