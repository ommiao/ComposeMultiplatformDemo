package model

import kotlinx.serialization.Serializable

@Serializable
data class NewsItem(
    val title: String,
    val imgList: List<String>,
    val source: String,
    val newsId: String,
    val digest: String,
    val postTime: String,
    val videoList: List<String>?
)