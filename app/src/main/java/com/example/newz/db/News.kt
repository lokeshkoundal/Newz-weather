package com.example.newz.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.newz.newz.models.Source


@Entity(tableName = "news_table")
data class News(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String,
    val urlToImage: String
)
