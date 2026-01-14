package com.example.rmp_front.viewmodel.register



import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rmp_front.data.RegisterStep
import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.models.User
import com.example.rmp_front.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    private val _step = MutableStateFlow(RegisterStep.PHONE)
    val step: StateFlow<RegisterStep> = _step
    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val repository = AuthRepository(ServerClient)

    private val registerUseCase = RegisterUseCase(repository)

    fun checkPhoneNumber(phone: String) = viewModelScope.launch {
        val result = registerUseCase.validatePhone(phone)

        result.onSuccess {
            _step.value = RegisterStep.PASSWORD
            _error.value = null
        }.onFailure { e ->
            _error.value = e.message
        }
    }

    fun backToPhone() {
        _step.value = RegisterStep.PHONE
    }

    fun register(phone: String, password: String) {
        viewModelScope.launch {
            val result = registerUseCase.invoke(phone, password)

            result.onSuccess { user ->
                _user.value = user
                _error.value = null
            }.onFailure { e ->
                _error.value = e.message
            }
        }
    }

}