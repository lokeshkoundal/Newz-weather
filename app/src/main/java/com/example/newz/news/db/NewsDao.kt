package com.example.newz.news.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
     fun insert(news: News) :Long

    @Query("SELECT * FROM news_table")
     fun getAllNews():LiveData<List<News>>

    @Query("DELETE FROM news_table WHERE id = :newsId")
     fun deleteNews(newsId : Int) :Int

    @Query("DELETE FROM news_table WHERE title = :newsTitle")
    fun deleteNews(newsTitle : String) :Int

    @Query("DELETE FROM news_table")
     fun deleteAll() : Int

}