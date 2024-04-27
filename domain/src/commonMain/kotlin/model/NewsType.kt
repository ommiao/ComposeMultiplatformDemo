package model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NewsType(
    @SerialName("typeId") val id: Int,
    @SerialName("typeName") val name: String
)
