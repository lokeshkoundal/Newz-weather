package com.example.newz.newz.network

import com.example.newz.newz.models.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApiService {

    @Headers("Authorization: bf2ae757784342e8a1c862ce01cb37fc")
    @GET("/v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,@Query("category") category : String
    ): Response<NewsModel>
}