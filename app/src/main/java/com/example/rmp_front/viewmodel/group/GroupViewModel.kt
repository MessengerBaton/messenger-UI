package com.example.rmp_front.viewmodel.group


import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.SessionManager
import com.example.rmp_front.data.models.Group
import com.example.rmp_front.data.models.Message
import com.example.rmp_front.data.repository.ChatRepository
import com.example.rmp_front.data.repository.GroupRepository
import com.example.rmp_front.viewmodel.chat.ChatUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GroupViewModel : ViewModel() {

    private val repository = ChatRepository(ServerClient)
    private val groupRepository = GroupRepository(ServerClient)
    private val groupUseCase = GroupUseCase(groupRepository)
    private val chatUseCase = ChatUseCase(repository)

    private val _group = MutableStateFlow<Group?>(null)
    val group: StateFlow<Group?> = _group

    private val _messages = MutableStateFlow<List<Message>>(emptyList())
    val message: StateFlow<List<Message>> = _messages

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadGroup(userId: String, groupId: String) {
        viewModelScope.launch {
            val chat = async { groupUseCase(groupId) }
            val chatResult = chat.await()

            val messages = async { chatUseCase.getChatMessages(userId, groupId) }
            val messagesResult = messages.await()

            chatResult.onSuccess {
                _group.value = it
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
