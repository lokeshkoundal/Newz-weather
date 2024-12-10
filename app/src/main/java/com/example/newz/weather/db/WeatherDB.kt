package com.example.newz.weather.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [WeatherRoom::class], version = 1, exportSchema = false)
abstract class WeatherDB() : RoomDatabase() {

    abstract fun weatherDao() : WeatherDao

    companion object{
        @Volatile
        private var INSTANCE: WeatherDB? = null

        fun getWeatherDao(context: Context): WeatherDao {
            if (INSTANCE == null) {
                synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        WeatherDB::class.java,
                        "weather_db")
                        .build()

                    INSTANCE = instance
                    return instance.weatherDao()

                }
            }
            else{
                return INSTANCE!!.weatherDao()

            }
        }
    }
}