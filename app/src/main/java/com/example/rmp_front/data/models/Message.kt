package com.example.rmp_front.data.models

import kotlinx.serialization.Serializable

@Serializable
data class Message(
    val id: String,
    val text: String,
    val senderId: String,
    val chatId: String? = null,
    val timestamp: String,
    val replyToId: String? = null,
    val isFromMe: Boolean? = null,
    val isRead: Boolean = false
)