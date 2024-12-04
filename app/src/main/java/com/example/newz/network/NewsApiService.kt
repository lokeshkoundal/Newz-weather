package com.example.newz.network

import com.example.newz.models.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApiService {

    @Headers("Authorization: 2d55feb44c4845f4a22d18041deaba12")
    @GET("/v2/top-headlines?pageSize=10")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("category") category : String,
        @Query("page") page : Int
    ): Response<NewsModel>
}