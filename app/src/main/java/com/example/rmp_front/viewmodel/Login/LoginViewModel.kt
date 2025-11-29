package com.example.rmp_front.viewmodel.Login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rmp_front.data.LoginResponse
import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _response = MutableStateFlow(false)
    val response: StateFlow<Boolean?> = _response

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val repository = AuthRepository(ServerClient)

    private val loginUseCase = LoginUseCase(repository)

        fun checkPersonData(phone: String, password: String) {
        viewModelScope.launch {
            val result = loginUseCase(phone, password)

            result.onSuccess {
                _response.value = true
            }.onSuccess {
                _response.value = false
            }

        }
    }

}