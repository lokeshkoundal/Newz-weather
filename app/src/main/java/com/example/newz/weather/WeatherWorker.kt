package com.example.newz.weather

import android.content.Context
import android.util.Log
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.newz.weather.network.WeatherApiService
import javax.inject.Inject

@HiltWorker
class WeatherWorker @Inject constructor(appContext : Context, workerParams: WorkerParameters,private val weatherApiService: WeatherApiService) : CoroutineWorker(appContext, workerParams) {

//    @Inject
//    lateinit var weatherApiService: WeatherApiService

    override suspend fun doWork(): Result {
        val weatherData = weatherApiService.getWeatherData("gurgaon").body()

        val temp_c = weatherData?.current?.temp_c
        val wind_mph = weatherData?.current?.wind_mph
        val humidity = weatherData?.current?.humidity
        val condition = weatherData?.current?.condition?.text
        val last_updated = weatherData?.current?.last_updated
        val city = weatherData?.location?.name
        val country = weatherData?.location?.country
        val icon = weatherData?.current?.condition?.icon
        val wind_dir = weatherData?.current?.wind_dir
        val uv = weatherData?.current?.uv
        val vis_km = weatherData?.current?.vis_km
        val feelslike_c = weatherData?.current?.feelslike_c
        val cloud = weatherData?.current?.cloud
        val is_day = weatherData?.current?.is_day
        val condition_icon = weatherData?.current?.condition?.icon
        val condition_text = weatherData?.current?.condition?.text
        val condition_code = weatherData?.current?.condition?.code


        Log.d("SaveWeatherDataToRoomTask", temp_c.toString())
        Log.d("SaveWeatherDataToRoomTask", wind_mph.toString())
        Log.d("SaveWeatherDataToRoomTask", humidity.toString())
        Log.d("SaveWeatherDataToRoomTask", condition.toString())
        Log.d("SaveWeatherDataToRoomTask", last_updated.toString())
        Log.d("SaveWeatherDataToRoomTask", city.toString())
        Log.d("SaveWeatherDataToRoomTask", country.toString())
        Log.d("SaveWeatherDataToRoomTask", icon.toString())
        Log.d("SaveWeatherDataToRoomTask", wind_dir.toString())
        Log.d("SaveWeatherDataToRoomTask", uv.toString())
        Log.d("SaveWeatherDataToRoomTask", vis_km.toString())

        return Result.success()


    }
}