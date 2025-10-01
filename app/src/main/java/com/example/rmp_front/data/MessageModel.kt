package com.example.rmp_front.data

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