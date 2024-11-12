package com.example.newz.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface NewsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(news: News)

    @Query("SELECT * FROM news_table")
    fun getAllNews(): List<News>

    @Query("DELETE FROM news_table WHERE id = :newsId")
    fun deleteNews(newsId : Int)

}