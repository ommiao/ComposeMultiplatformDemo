package model

import kotlinx.serialization.Serializable

@Serializable
data class NewsDetail(
    val id: Int,
    val items: List<NewsDetailItem>
)

@Serializable
data class NewsDetailItem(
    val type: String,
    val content: String,
    val imageUrl: String?,
    val videoUrl: String?
)
