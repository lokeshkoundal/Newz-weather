package com.example.newz.weather.network

import com.example.newz.weather.models.Weather
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface WeatherApiService {

    @Headers("Authorization: fc97ee6850a54a7192890610240612")
    @GET("/current.json")
    suspend fun getWeatherData(
        @Query("q") city : String
    ) : Response<Weather>

}