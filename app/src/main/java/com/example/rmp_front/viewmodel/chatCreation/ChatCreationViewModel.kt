package com.example.rmp_front.viewmodel.chatCreation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.models.Chat
import com.example.rmp_front.data.models.Group
import com.example.rmp_front.data.models.User
import com.example.rmp_front.data.repository.ChatRepository
import com.example.rmp_front.data.repository.GroupRepository
import com.example.rmp_front.data.repository.UserRepository
import com.example.rmp_front.ui_component.screens.ChatCreationScreen
import com.example.rmp_front.viewmodel.chatsList.ChatsListUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatCreationViewModel : ViewModel() {

    private val userRepository = UserRepository(ServerClient)
    private val groupRepository = GroupRepository(ServerClient)
    private val chatRepository = ChatRepository(ServerClient)

    private val chatsCreationUseCase = ChatCreationUseCase(userRepository, groupRepository, chatRepository)


    private val _chat = MutableStateFlow<Chat?>(null)
    val chat: StateFlow<Chat?> = _chat

    private val _group = MutableStateFlow<Group?>(null)
    val group: StateFlow<Group?> = _group

    private val _users = MutableStateFlow<List<User>>(emptyList())
    val users: StateFlow<List<User>> = _users

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error


    fun createGroup(userId: String, chatTitle: String, users: List<User>) {
        viewModelScope.launch {
            val result = chatsCreationUseCase.createGroup(userId, chatTitle, users)

            result.onSuccess {
                _group.value = it
            }.onFailure {
                _error.value = it.message ?: "Unknown error"
            }
        }
    }

    fun createChat(userId: String, user: User) {
        viewModelScope.launch {
            val result = chatsCreationUseCase.createChat(userId, user)

            result.onSuccess {
                _chat.value = it
            }.onFailure {
                _error.value = it.message ?: "Unknown error"
            }
        }
    }

    fun getUsers() {
        viewModelScope.launch {
            val result = chatsCreationUseCase.getUsers()

            result.onSuccess {
                _users.value = it
            }.onFailure {
                _error.value = it.message ?: "Unknown error"
            }
        }
    }
}