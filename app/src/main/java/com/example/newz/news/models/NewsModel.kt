package com.example.newz.news.models

data class NewsModel(
    var articles: List<Article>,
    val status: String,
    val totalResults: Int
)