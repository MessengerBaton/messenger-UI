package com.example.rmp_front.data.dto

import android.annotation.SuppressLint
import com.example.rmp_front.data.models.Chat
import com.example.rmp_front.data.models.Message
import kotlinx.serialization.Serializable
@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class MessageDto(
    val id: String,
    val chatId: String,
    val text: String,
    val timestamp: String,
    val isFromMe: Boolean
)

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class MessageListResponse(
    val messages: List<MessageDto>
)


fun MessageDto.toDomain() = Message(
    id = id,
    chatId = chatId,
    text = text,
    isFromMe = isFromMe,
    timestamp = timestamp
)