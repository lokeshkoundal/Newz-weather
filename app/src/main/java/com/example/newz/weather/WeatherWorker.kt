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

                    val tempC = weatherData?.current?.temp_c
                    val windMph = weatherData?.current?.wind_mph
                    val humidity = weatherData?.current?.humidity
                    val conditionText = weatherData?.current?.condition?.text
                    val lastUpdated = weatherData?.current?.last_updated
                    val cityName = weatherData?.location?.name
                    val region = weatherData?.location?.region
                    val country = weatherData?.location?.country
                    val icon = weatherData?.current?.condition?.icon
                    val feelslikeC = weatherData?.current?.feelslike_c
                    val isDay = weatherData?.current?.is_day
                    val precipMM = weatherData?.current?.precip_mm


                    val weatherDataForDB = WeatherRoom(
                        name = cityName?:"",
                        region = region?:"",
                        country = country?:"",
                        last_updated = lastUpdated?:"",
                        temp_c = tempC?:0.0,
                        wind_mph = windMph?:0.0,
                        humidity = humidity!!,
                        icon_url = icon!!,
                        precip_mm = precipMM!!,
                        is_day = isDay!!,
                        condition_text = conditionText!!,
                        feelslike_c = feelslikeC!!
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
