package com.example.rmp_front.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class MessageDto(
    val id: String,
    val chatId: String,
    val senderId: String,
    val text: String?,
    val timestamp: String
)


@Serializable
data class MessageListResponse(
    val messages: List<MessageDto>
)
