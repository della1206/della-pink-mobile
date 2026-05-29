package com.example.della_pink.model

data class NewsResponse(
    val articles: List<Article>
)

data class Article(
    val title: String,
    val description: String?,
    val urlToImage: String?
)