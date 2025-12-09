package com.example.rmp_front.data.models

import com.example.rmp_front.data.dto.MessageDto


data class Message(
    val id: String,
    val text: String,
    val chatId: String,
    val timestamp: String,
    val replyToId: String? = null,
    val isFromMe: Boolean,
    val isRead: Boolean? = false
)

fun Message.toDto() = MessageDto (
    id = id,
    chatId = chatId,
    text = text,

    isFromMe = isFromMe,
    timestamp = timestamp
)