package com.example.rmp_front.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rmp_front.data.LoginResponse
import com.example.rmp_front.data.ServerClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _response = MutableStateFlow<LoginResponse?>(null)
    val response: StateFlow<LoginResponse?> = _response

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun checkPersonData(phone: String, password: String) {
        viewModelScope.launch {
            try {
                _response.value = ServerClient.login(phone, password)
            } catch(e: Exception) {
                _error.value = "че-то ошибка: ${e.message}"
            }

        }
    }
}
