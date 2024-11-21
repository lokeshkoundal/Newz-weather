package com.example.newz.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import retrofit2.Response


@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(news: News)

    @Query("SELECT * FROM news_table")
    suspend fun getAllNews(): LiveData<List<News>>

    @Query("DELETE FROM news_table WHERE id = :newsId")
    suspend fun deleteNews(newsId : Int)

    @Query("DELETE FROM news_table")
    suspend fun deleteAll() : Response<News>

}