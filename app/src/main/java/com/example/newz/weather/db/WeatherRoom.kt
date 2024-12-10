package com.example.newz.weather.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "weather_table")
data class WeatherRoom(
    @PrimaryKey
    val name: String,
    val region: String,
    val country : String,
    val last_updated: String,
    val temp_c: Double,
    val is_day: Int,
    val condition_text: String,
    val feelslike_c: Double,
    val icon_url: String,
    val wind_mph: Double?,
    val humidity: Int?,
    val precip_mm : Int?,

)
