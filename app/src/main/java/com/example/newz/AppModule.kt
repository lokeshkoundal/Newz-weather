package com.example.newz

import android.content.Context
import com.example.newz.news.db.NewsDB
import com.example.newz.news.db.NewsDao
import com.example.newz.news.network.NewsApiService
import com.example.newz.weather.db.WeatherDB
import com.example.newz.weather.db.WeatherDao
import com.example.newz.weather.network.WeatherApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideNewsApiService(): NewsApiService {
        return RetrofitObj.api.create(NewsApiService::class.java)
    }

    @Provides
    @Singleton
    fun providesNewsRoomDB(@ApplicationContext context: Context): NewsDao {
        return NewsDB.getNewsDao(context)
    }

    @Provides
    @Singleton
    fun providesWeatherApiService(): WeatherApiService {
        return RetrofitObj.weatherAPI.create(WeatherApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideWeatherRoomDB(@ApplicationContext context: Context): WeatherDao {
        return WeatherDB.getWeatherDao(context)
    }


}