package com.example.rmp_front.viewmodel.register



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val _response = MutableStateFlow<Boolean?>(null)
    val response: StateFlow<Boolean?> = _response

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val repository = AuthRepository(ServerClient)

    private val registerUseCase = RegisterUseCase(repository)

    fun checkPhoneNumber(phone: String) = viewModelScope.launch {
        val result = registerUseCase.validatePhone(phone)

        result.onSuccess {
            _response.value = null
            _error.value = null
        }.onFailure { e ->
            _response.value = false
            _error.value = e.message
        }
    }

    fun register(phone: String, password: String) {
        viewModelScope.launch {
            val result = registerUseCase.invoke(phone, password)

            result.onSuccess {
                _response.value = true
                _error.value = null
            }.onFailure { e ->
                _response.value = false
                _error.value = e.message
            }
        }
    }

}