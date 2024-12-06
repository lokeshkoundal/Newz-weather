package com.example.newz.weather.models

data class Current(
    val cloud: Int,
    val condition: Condition,
    val feelslike_c: Double,
    val humidity: Int,
    val is_day: Int,
    val last_updated: String,
    val temp_c: Double,
    val uv: Double,
    val vis_km: Int,
    val wind_dir: String,
    val wind_mph: Double
)