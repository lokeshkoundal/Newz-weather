package com.example.newz.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "news_table")
data class News(
    @PrimaryKey(autoGenerate = true)
    val id: Int?,
    val author: String,
    val content: String,
    val description: String,
    val publishedAt: String,
    val source: String,
    val title: String,
    val url: String,
    val urlToImage: String
)
