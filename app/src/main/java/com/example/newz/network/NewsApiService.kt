package com.example.newz.network

import com.example.newz.models.NewsModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsApiService {

    @Headers("Authorization: a69974cf3c4c4f6a9793d9fca27cc77d")
    @GET("/v2/top-headlines?pageSize=10")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
        @Query("category") category : String,
        @Query("page") page : Int
    ): Response<NewsModel>
}