package com.example.newz.weather

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.newz.RetrofitObj
import com.example.newz.weather.db.WeatherDB
import com.example.newz.weather.db.WeatherRoom
import com.example.newz.weather.network.WeatherApiService
import java.net.ConnectException
import java.net.HttpRetryException

//@HiltWorker
class WeatherWorker(appContext : Context, workerParams: WorkerParameters) : CoroutineWorker(appContext, workerParams) {

//    @Inject
//    lateinit var weatherApiService: WeatherApiService


    override suspend fun doWork(): Result {

        try {
            val weatherApiService = RetrofitObj.weatherAPI.create(WeatherApiService::class.java)
            val weatherDao = WeatherDB.getWeatherDao(applicationContext)

            val cities = listOf("gurgaon", "manali", "una,himachal", "delhi")

            cities.forEach {
                    val weatherData = weatherApiService.getWeatherData(it).body()

                    val temp_c = weatherData?.current?.temp_c
                    val wind_mph = weatherData?.current?.wind_mph
                    val humidity = weatherData?.current?.humidity
                    val condition_text = weatherData?.current?.condition?.text
                    val last_updated = weatherData?.current?.last_updated
                    val cityName = weatherData?.location?.name
                    val region = weatherData?.location?.region
                    val country = weatherData?.location?.country
                    val icon = weatherData?.current?.condition?.icon
                    val feelslike_c = weatherData?.current?.feelslike_c
                    val is_day = weatherData?.current?.is_day
                    val precip_mm = weatherData?.current?.precip_mm


                    val weatherDataForDB = WeatherRoom(
                        name = cityName!!,
                        region = region!!,
                        country = country!!,
                        last_updated = last_updated!!,
                        temp_c = temp_c!!,
                        wind_mph = wind_mph!!,
                        humidity = humidity!!,
                        icon_url = icon!!,
                        precip_mm = precip_mm!!,
                        is_day = is_day!!,
                        condition_text = condition_text!!,
                        feelslike_c = feelslike_c!!
                    )

                    weatherDao.insertWeather(weatherDataForDB)

            }.also {
                    return Result.success()
            }

            } catch (e: HttpRetryException) {
                Log.d("WeatherWorker Exception", e.message.toString())
                return Result.retry()
            } catch (e: ConnectException) {
                Log.d("WeatherWorker Exception", e.message.toString())
                return Result.retry()
            } catch (e: Exception) {
                Log.d("WeatherWorker Exception", e.message.toString())
                return Result.failure()
            } catch (e: NullPointerException) {
                Log.d("WeatherWorker Exception", e.message.toString())
                return Result.failure()
            }
    }
}
