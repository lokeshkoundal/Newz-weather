package com.example.newz.models

data class NewsModel(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)