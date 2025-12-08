package com.example.rmp_front.data.dto

import com.example.rmp_front.data.models.Chat
import com.example.rmp_front.data.models.Message
import kotlinx.serialization.Serializable

@Serializable
data class ChatDto(
    val id: String,
    val userId: String,
    val title: String,
    val lastMessage: MessageDto,
    val avatarUrl: String?,
)

@Serializable
data class ChatInfoDto(
    val id: String,
    val userId: String,
    val title: String,
    val avatarUrl: String?,
)


@Serializable
data class ChatListResponse(
    val chats: List<ChatDto>
)


fun ChatDto.toDomain() = Chat(
    id = id,
    userId = userId,
    title = title,
    lastMessage = lastMessage.toDomain().text,
    timestamp = lastMessage.toDomain().timestamp,
    avatarUrl = avatarUrl
)


fun ChatInfoDto.toDomain() = Chat(
    id = id,
    userId = userId,
    title = title,
    avatarUrl = avatarUrl
)