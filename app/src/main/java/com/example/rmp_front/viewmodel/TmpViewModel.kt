package com.example.rmp_front.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rmp_front.data.MessageModel
import com.example.rmp_front.data.ServerClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

/**
 * вьюмодель для тмпшного эндпоинта
 * (соответственно для новых надо будет писать аналогичные)
 **/
class TmpViewModel : ViewModel() {
    private val _messages = MutableStateFlow<List<MessageModel>?>(null)
    val messages: StateFlow<List<MessageModel>?> = _messages

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    init {
        fetchTempMessage()
    }

    private fun fetchTempMessage() {
        viewModelScope.launch {
            try {
                _messages.value = ServerClient.getTemp()
            } catch (e: Exception) {
                _error.value = "че-то ошибка: ${e.message}"
            }
        }
    }
}
