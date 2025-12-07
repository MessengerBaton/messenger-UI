package com.example.rmp_front.data.dto

import com.example.rmp_front.data.models.Chat
import com.example.rmp_front.data.models.Message
import kotlinx.serialization.Serializable

@Serializable
data class ChatDto(
    val id: String,
    val title: String,
    val lastMessage: MessageShortDto,
    val avatarUrl: String?,
)

@Serializable
data class MessageShortDto(
    val id: String,
    val senderId: String,
    val text: String,
    val timestamp: String
)


@Serializable
data class ChatListResponse(
    val chats: List<ChatDto>
)


fun ChatDto.toDomain() = Chat(
    id = id,
    title = title,
    lastMessage = lastMessage.toDomain().text,
    timestamp = lastMessage.toDomain().timestamp,
    avatarUrl = avatarUrl
)

fun MessageShortDto.toDomain() = Message(
    id = id,
    senderId = senderId,
    text = text,
    timestamp = timestamp
)
