package com.example.newz.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newz.room.UserDatabase

@Database(entities = [News::class], version = 1, exportSchema = false)
abstract class NewsDB : RoomDatabase() {

        abstract fun newsDao() : NewsDao

        companion object{
                @Volatile
                private var INSTANCE: NewsDB? = null

                fun getDatabase(context: Context): NewsDB {

                        return INSTANCE?: synchronized(this){
                                val instance = Room.databaseBuilder(
                                        context.applicationContext,
                                        NewsDB::class.java,
                                        "news_db").build()

                                INSTANCE = instance
                                return instance
                        }
                }
        }
}