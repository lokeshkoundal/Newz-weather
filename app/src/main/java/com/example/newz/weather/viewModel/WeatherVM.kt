package com.example.newz.weather.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.newz.weather.db.WeatherDao
import com.example.newz.weather.db.WeatherRoom
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherVM @Inject constructor(private val weatherDao : WeatherDao ) :ViewModel() {

    var weatherData  : LiveData<WeatherRoom> = weatherDao.getWeatherData()
}