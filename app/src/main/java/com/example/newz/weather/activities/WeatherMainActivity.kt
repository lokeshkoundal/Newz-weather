package com.example.newz.weather.activities

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.Pager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.newz.R
import com.example.newz.databinding.ActivityWeatherMainBinding
import com.example.newz.weather.db.WeatherRoom
import com.example.newz.weather.viewModel.WeatherVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherMainActivity : AppCompatActivity() {
    private lateinit var viewPager: ViewPager2
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private val weatherVM: WeatherVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather2)

        viewPager = findViewById(R.id.viewPager)

        weatherVM.weatherList.observe(this) {
            viewPagerAdapter = ViewPagerAdapter(it, this)
            viewPager.adapter = viewPagerAdapter

        }
    }


    inner class ViewPagerAdapter(val list: List<WeatherRoom>, val context: Context) :
        RecyclerView.Adapter<PagerViewHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
//        val view: View = LayoutInflater.from(parent.context)
//            .inflate(R.layout.activity_weather_main, parent, false)
//        return PagerViewHolder(view)

            val binding = ActivityWeatherMainBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return PagerViewHolder(binding)
        }

        override fun getItemCount(): Int {
            return list.size
        }

        override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {

            holder.binding.weatherData = list[position]
            list[position].apply {

//                holder.binding.cityTv.text = name
//            holder.regionCountryTv.text = region + ", " + country
//            holder.tempTv.text = temp_c.toString() + "\u2103"
//                holder.binding.weatherNameTv.text = condition_text
                holder.binding.dateTv.text = parseTimestampManually(last_updated)
//                holder.binding.feelsLikeTv.text = feelslike_c.toString().plus("\u2103")
//                holder.binding.humidityTv.text = humidity.toString().plus("%")
//                holder.binding.windSpeedTv.text = wind_mph.toString().plus(" mph")
//                holder.binding.precipitationTv.text = precip_mm.toString().plus("mm")


                if (is_day == 1) {
                    holder.binding.bgImage.setImageResource(R.drawable.ic_day)
                } else {
                    holder.binding.bgImage.setImageResource(R.drawable.ic_night2)
                }


                Glide.with(context) // or use 'this' if inside an Activity, 'requireContext()' in a Fragment
                    .load("https://".plus(icon_url))
                    .placeholder(R.drawable.baseline_replay_24) // Optional: add a placeholder while loading
                    .error(R.drawable.ic_error) // Optional: add an error image in case of failure
                    .into(holder.binding.weatherImage)

            }

        }
    }

    private fun parseTimestampManually(timestamp: String): String {
        val (datePart, timePart) = timestamp.split(" ") // Split into date and time
        val (year, month, day) = datePart.split("-").map { it.toInt() }
        val (hour, minute) = timePart.split(":")
        return "$day ${Months.entries[month - 1]} $year, $hour:$minute"
    }


    class PagerViewHolder(val binding: ActivityWeatherMainBinding) : RecyclerView.ViewHolder(binding.root)

    enum class Months {
        Jan,
        Feb,
        Mar,
        April,
        May,
        June,
        July,
        Aug,
        Sep,
        Oct,
        Nov,
        Dec
    }

}
