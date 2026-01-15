package com.example.rmp_front.viewmodel.chatsList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.SessionManager
import com.example.rmp_front.data.models.Chat
import com.example.rmp_front.data.models.Group
import com.example.rmp_front.data.models.User
import com.example.rmp_front.data.repository.ChatRepository
import com.example.rmp_front.data.repository.GroupRepository
import com.example.rmp_front.data.repository.UserRepository
import com.example.rmp_front.viewmodel.user.UserUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
class ChatsListViewModel : ViewModel() {

    private val chatRepository = ChatRepository(ServerClient)
    private val groupRepository = GroupRepository(ServerClient)
    private val chatsListUseCase = ChatsListUseCase(chatRepository, groupRepository)

    private val _chats = MutableStateFlow<List<Chat>>(emptyList())
    val chats: StateFlow<List<Chat>> = _chats

    private val _groups = MutableStateFlow<List<Group>>(emptyList())
    val groups: StateFlow<List<Group>> = _groups

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadAll(userId: String) {
        loadChats(userId)
        loadGroups(userId)
    }

    private fun loadChats(userId: String) {
        viewModelScope.launch {
            chatsListUseCase.loadChats(userId)
                .onSuccess { _chats.value = it }
                .onFailure { _error.value = it.message }
        }
    }

    private fun loadGroups(userId: String) {
        viewModelScope.launch {
            chatsListUseCase.loadGroups(userId)
                .onSuccess { _groups.value = it }
                .onFailure { _error.value = it.message }
        }
    }
}
