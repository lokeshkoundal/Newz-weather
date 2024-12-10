package com.example.newz.weather.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertWeather(weatherRoom: WeatherRoom)

    @Query("SELECT * FROM weather_table")
    fun getWeatherData() : LiveData<WeatherRoom>
}