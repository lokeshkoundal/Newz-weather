package com.example.newz

import android.content.Context
import com.example.newz.db.NewsDB
import com.example.newz.db.NewsDao
import com.example.newz.network.NewsApiService
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
    fun providesRoomDB(@ApplicationContext context: Context): NewsDao {
        return NewsDB.getNewsDao(context)
    }


}