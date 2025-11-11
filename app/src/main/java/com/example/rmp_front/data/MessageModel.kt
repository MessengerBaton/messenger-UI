package com.example.rmp_front.data

import kotlinx.serialization.InternalSerializationApi
import kotlinx.serialization.Serializable

// MessageModel - модель сообщения согласованная с бэком
@OptIn(InternalSerializationApi::class)
@Serializable
data class MessageModel (
    val id: String? = null,
    val chatId: String,
    val senderId: String,
    val body: String,
    val status: String = "sent",
    val createdAt: String = "",
    val editedAt: String? = null,
    val deletedAt: String? = null,
)