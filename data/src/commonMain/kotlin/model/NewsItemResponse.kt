package model

import kotlinx.serialization.Serializable

@Serializable
data class NewsListResponse(
    val code: Int,
    val msg: String,
    val data: List<NewsItem>
)