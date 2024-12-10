package com.example.newz.news.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [News::class], version = 1, exportSchema = false)
abstract class NewsDB : RoomDatabase() {

        abstract fun newsDao() : NewsDao

        companion object{
                @Volatile
                private var INSTANCE: NewsDB? = null

                fun getNewsDao(context: Context): NewsDao {

                        if(INSTANCE ==null) {
                                synchronized(this) {
                                        val instance = Room.databaseBuilder(
                                                context.applicationContext,
                                                NewsDB::class.java,
                                                "news_db"
                                        ).build()

                                        INSTANCE = instance
                                        return instance.newsDao()
                                }
                        }else{
                                return INSTANCE!!.newsDao()
                        }

                }
        }
}