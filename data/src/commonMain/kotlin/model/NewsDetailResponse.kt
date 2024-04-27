package model

import kotlinx.serialization.Serializable

@Serializable
data class NewsDetailResponse(
    val code: Int,
    val msg: String,
    val data: NewsDetail
)