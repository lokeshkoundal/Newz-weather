package com.example.newz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.newz.activities.MainActivity
import com.example.newz.weather.activities.WeatherMainActivity

class NavigationActivity : AppCompatActivity() {

    lateinit var newsBtn: AppCompatButton
    lateinit var weatherBtn: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        newsBtn = findViewById(R.id.btnNews)
        weatherBtn = findViewById(R.id.btnWeather)

        newsBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        weatherBtn.setOnClickListener{
            val intent = Intent(this, WeatherMainActivity::class.java)
            startActivity(intent)
        }

    }
}