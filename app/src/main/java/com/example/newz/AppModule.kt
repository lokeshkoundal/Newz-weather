package com.example.newz

import com.example.newz.newz.network.NewsApiService
import com.example.newz.newz.network.RetrofitObj
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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

}