package com.example.rmp_front.data.models

data class Chat(
    val id: String,
    val userId: String,
    val friendId: String? ,
    val title: String,
    val lastMessage: String? = null,
    val timestamp: String? = null,
    val avatarUrl: String? = null
)