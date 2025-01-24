package com.example.newz

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.example.newz.databinding.ActivityNavigationBinding
import com.example.newz.navigation.NavigationComponent
import com.example.newz.news.activities.MainActivity
import com.example.newz.weather.activities.WeatherMainActivity

class NavigationActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding : ActivityNavigationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnNews.setOnClickListener(this)
        binding.btnWeather.setOnClickListener(this)
        binding.btnNavigation.setOnClickListener(this)

    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btnNews -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.btnWeather -> {
                val intent = Intent(this, WeatherMainActivity::class.java)
                startActivity(intent)
            }
            R.id.btnNavigation -> {
                val intent = Intent(this, NavigationComponent::class.java)
                startActivity(intent)
            }
        }
    }
}