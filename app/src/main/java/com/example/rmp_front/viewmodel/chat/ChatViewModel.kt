package com.example.rmp_front.viewmodel.chat

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.SessionManager
import com.example.rmp_front.data.models.Chat
import com.example.rmp_front.data.models.Message
import com.example.rmp_front.data.repository.ChatRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


class ChatViewModel() : ViewModel() {

    private val chatRepository = ChatRepository(ServerClient)
    private val chatUseCase = ChatUseCase(chatRepository)

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val message: StateFlow<List<Message>> = _messages

    private val _chat = MutableStateFlow<Chat?>(null)
    val chat: StateFlow<Chat?> = _chat


    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading



    fun loadChat(userId: String, chatId: String) {
        viewModelScope.launch {
            val chat = async { chatUseCase.getChatInfo(chatId) }
            val chatResult = chat.await()

            val messages = async { chatUseCase.getChatMessages(userId, chatId) }
            val messagesResult = messages.await()

            chatResult.onSuccess {
                _chat.value = it
            }.onFailure {
                _error.value =  it.message ?: "Unknown error with loading chat"
            }

            messagesResult.onSuccess {
                _messages.value = it
            }.onFailure {
                _error.value =  it.message ?: "Unknown error with loading messages"
            }
        }
    }



    fun sendMessage(context: Context, message: Message) {
        viewModelScope.launch {
            val userId = SessionManager.getUserId(context)
            if (userId != null) {
                val sendResult = chatUseCase.sendMessage(userId, message)

                sendResult.onFailure {
                    _error.value = it.message ?: "Unknown error"
                    return@launch
                }

                val messagesResult = chatUseCase.getChatMessages(userId, message.chatId)
                messagesResult.onSuccess {
                    _messages.value = it
                }.onFailure {
                    _error.value = it.message ?: "Unknown error"
                }

            }
        }
    }
}
