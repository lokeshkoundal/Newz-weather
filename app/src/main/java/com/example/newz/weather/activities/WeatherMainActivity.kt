package com.example.newz.weather.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.newz.R
import com.example.newz.weather.viewModel.WeatherVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherMainActivity : AppCompatActivity() {

    private val weatherVM: WeatherVM by viewModels()
    lateinit var cityTv: TextView
    lateinit var regionCountryTv: TextView
    lateinit var tempTv: TextView
    lateinit var weatherNameTv: TextView
    lateinit var dateTv: TextView
    lateinit var feelsLikeTv: TextView
    lateinit var humidityTv: TextView
    lateinit var windSpeedTv: TextView
    lateinit var precipitationTv: TextView
    lateinit var weatherIconIv: ImageView
    lateinit var bgImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather_main)
        cityTv = findViewById(R.id.cityTv)
        regionCountryTv = findViewById(R.id.region_countryTv)
        tempTv = findViewById(R.id.tempTv)
        weatherNameTv = findViewById(R.id.weatherNameTv)
        dateTv = findViewById(R.id.dateTv)
        feelsLikeTv = findViewById(R.id.feelsLikeTv)
        humidityTv = findViewById(R.id.humidityTv)
        windSpeedTv = findViewById(R.id.windSpeedTv)
        precipitationTv = findViewById(R.id.precipitationTv)
        weatherIconIv = findViewById(R.id.weatherImage)
        bgImage = findViewById(R.id.bg_image)


        weatherVM.weatherData.observe(this) {

            it.apply {
                cityTv.text = name
                regionCountryTv.text = region + ", " + country
                tempTv.text = temp_c.toString() + "\u2103"
                weatherNameTv.text = condition_text
                dateTv.text = last_updated
                feelsLikeTv.text = feelslike_c.toString().plus("\u2103")
                humidityTv.text = humidity.toString().plus("%")
                windSpeedTv.text = wind_mph.toString().plus(" mph")
                precipitationTv.text = precip_mm.toString().plus("mm")

                it.apply {
                    if (is_day == 1) {
                        bgImage.setImageResource(R.drawable.ic_day)
                    } else {
                        bgImage.setImageResource(R.drawable.ic_night)
                    }

                    Glide.with(this@WeatherMainActivity) // or use 'this' if inside an Activity, 'requireContext()' in a Fragment
                        .load("https://".plus(icon_url))
                        .placeholder(R.drawable.baseline_replay_24) // Optional: add a placeholder while loading
                        .error(R.drawable.ic_error) // Optional: add an error image in case of failure
                        .into(weatherIconIv)

                }

            }
        }
    }
}