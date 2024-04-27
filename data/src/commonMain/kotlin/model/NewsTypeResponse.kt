package model

import kotlinx.serialization.Serializable

@Serializable
data class NewsTypeResponse(
    val code: Int,
    val msg: String,
    val data: List<NewsType>
)
