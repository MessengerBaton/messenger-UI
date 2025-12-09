package com.example.rmp_front.viewmodel.chatsList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.models.Chat
import com.example.rmp_front.data.models.User
import com.example.rmp_front.data.repository.ChatRepository
import com.example.rmp_front.data.repository.UserRepository
import com.example.rmp_front.viewmodel.user.UserUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

//data class ChatItem(val id: String, val name: String)

//class ChatsViewModel : ViewModel() {
//    private val _chats = MutableStateFlow(listOf(
//        ChatItem("chat_1", "Chat 1"),
//        ChatItem("chat_2", "Chat 2")
//    ))
//    val chats: StateFlow<List<ChatItem>> = _chats.asStateFlow()
//
//    // Здесь можно добавить логику загрузки чатов из API позже
//}


class ChatsListViewModel() : ViewModel() {

    private val chatsRepository = ChatRepository(ServerClient)
    private val chatsListUseCase = ChatsListUseCase(chatsRepository)

    private val _chats = MutableStateFlow<List<Chat>>(emptyList())
    val chats: StateFlow<List<Chat>> = _chats



    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    init {
        loadChats()
    }

    fun loadChats() {
        viewModelScope.launch {
            val chats = async { chatsListUseCase() }
            val chatsResult = chats.await()

            chatsResult.onSuccess {
                _chats.value = it
            }.onFailure {
                _error.value =  it.message ?: "Unknown error"
            }
        }
    }
}
