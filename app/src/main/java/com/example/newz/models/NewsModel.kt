package com.example.newz.models

data class NewsModel(
    var articles: List<Article>,
    val status: String,
    val totalResults: Int
)