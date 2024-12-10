package com.example.newz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.newz.navigation.NavigationComponent
import com.example.newz.news.activities.MainActivity
import com.example.newz.weather.activities.WeatherMainActivity

class NavigationActivity : AppCompatActivity() {
    private lateinit var newsBtn: AppCompatButton
    private lateinit var weatherBtn: AppCompatButton
    private lateinit var navigationBtn: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_navigation)

        newsBtn = findViewById(R.id.btnNews)
        weatherBtn = findViewById(R.id.btnWeather)
        navigationBtn = findViewById(R.id.btnNavigation)

        newsBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        weatherBtn.setOnClickListener{
            val intent = Intent(this, WeatherMainActivity::class.java)
            startActivity(intent)
        }
        navigationBtn.setOnClickListener{
            val intent = Intent(this, NavigationComponent::class.java)
            startActivity(intent)

        }

    }
}