package com.example.rmp_front.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

data class ChatItem(val id: String, val name: String)

class ChatsViewModel : ViewModel() {
    private val _chats = MutableStateFlow(listOf(
        ChatItem("chat_1", "Chat 1"),
        ChatItem("chat_2", "Chat 2")
    ))
    val chats: StateFlow<List<ChatItem>> = _chats.asStateFlow()

    // Здесь можно добавить логику загрузки чатов из API позже
}