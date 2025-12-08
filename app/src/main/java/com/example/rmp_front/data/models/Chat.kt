package com.example.rmp_front.data.models

data class Chat(
    val id: String,
    val userId: String,
    val title: String,
    val lastMessage: String? = null,
    val timestamp: String? = null,
    val unreadCount: Int = 0,
    val avatarUrl: String? = null,
    val isOnline: Boolean = false
)