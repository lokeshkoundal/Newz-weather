package com.example.newz.weather.models

data class Current(
    val condition: Condition,
    val feelslike_c: Double,
    val humidity: Int,
    val is_day: Int,
    val last_updated: String,
    val precip_mm: Int,
    val temp_c: Double,
    val wind_mph : Double
)