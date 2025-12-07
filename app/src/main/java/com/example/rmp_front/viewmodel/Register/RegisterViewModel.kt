package com.example.rmp_front.viewmodel.Register

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val _success = MutableStateFlow(false)
    val success: StateFlow<Boolean> = _success

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val repository = AuthRepository(ServerClient)
    private val registerUseCase = RegisterUseCase(repository)

    fun register(phone: String, password: String) {
        viewModelScope.launch {
            val result = registerUseCase(phone, password)
            result.onSuccess {
                _success.value = true
            }.onFailure {
                _error.value = it.message
                _success.value = false
            }
        }
    }
}
