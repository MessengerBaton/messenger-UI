package com.example.rmp_front.viewmodel.chatCreation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.models.Chat
import com.example.rmp_front.data.models.User
import com.example.rmp_front.data.repository.ChatRepository
import com.example.rmp_front.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatCreationViewModel : ViewModel() {

    private val userRepository = UserRepository(ServerClient)
    private val chatRepository = ChatRepository(ServerClient)

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _searchResults = MutableStateFlow<List<User>>(emptyList())
    val searchResults: StateFlow<List<User>> = _searchResults

    private val _selectedUsers = MutableStateFlow<List<User>>(emptyList())
    val selectedUsers: StateFlow<List<User>> = _selectedUsers

    private val _chatTitle = MutableStateFlow("")
    val chatTitle: StateFlow<String> = _chatTitle

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun updateSearchQuery(query: String) {
        _searchQuery.value = query
        if (query.length >= 2) {
            searchUsers(query)
        } else {
            _searchResults.value = emptyList()
        }
    }

    fun updateChatTitle(title: String) {
        _chatTitle.value = title
    }

    fun addUser(user: User) {
        val current = _selectedUsers.value.toMutableList()
        if (!current.any { it.id == user.id }) {
            current.add(user)
            _selectedUsers.value = current
            _searchQuery.value = ""
            _searchResults.value = emptyList()
        }
    }

    fun removeUser(user: User) {
        val current = _selectedUsers.value.toMutableList()
        current.removeAll { it.id == user.id }
        _selectedUsers.value = current
    }

    fun clearSelection() {
        _selectedUsers.value = emptyList()
    }

    private fun searchUsers(query: String) {
        viewModelScope.launch {
            _isLoading.value = true
            try {
                // В реальном приложении здесь будет вызов API поиска пользователей
                // Для примера, показываем заглушку
                _searchResults.value = listOf(
                    User(
                        id = "user_${query.hashCode()}",
                        name = "User $query",
                        nick = "@user_${query.lowercase()}",
                        phone = "+79001234567",
                        about = "Test user",
                        avatarUrl = null
                    ),
                    User(
                        id = "user_${query.hashCode() + 1}",
                        name = "Another $query",
                        nick = "@another_${query.lowercase()}",
                        phone = "+79007654321",
                        about = "Another test user",
                        avatarUrl = null
                    )
                )
            } catch (e: Exception) {
                _error.value = "Search failed: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    suspend fun createChat(): Chat? {
        if (_selectedUsers.value.isEmpty()) {
            _error.value = "Select at least one user"
            return null
        }

        return try {
            _isLoading.value = true

            // В реальном приложении здесь будет вызов API создания чата
            // Пока возвращаем заглушку
            val chat = Chat(
                id = "chat_${System.currentTimeMillis()}",
                userId = _selectedUsers.value.first().id,
                title = _chatTitle.value.ifBlank { _selectedUsers.value.first().name },
                lastMessage = "",
                timestamp = "",
                avatarUrl = null
            )

            // Сбрасываем состояние после создания
            resetState()
            chat

        } catch (e: Exception) {
            _error.value = "Failed to create chat: ${e.message}"
            null
        } finally {
            _isLoading.value = false
        }
    }

    fun resetState() {
        _searchQuery.value = ""
        _searchResults.value = emptyList()
        _selectedUsers.value = emptyList()
        _chatTitle.value = ""
        _error.value = null
    }
}
