package com.example.rmp_front.viewmodel.login

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.SessionManager
import com.example.rmp_front.data.models.User
import com.example.rmp_front.data.repository.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val repository = AuthRepository(ServerClient)

    private val loginUseCase = LoginUseCase(repository)

    fun checkPersonData(phone: String, password: String, context: Context) {
        viewModelScope.launch {
            val result = loginUseCase(phone, password)

            result.onSuccess { user ->
                _user.value = user
                _error.value = null

                SessionManager.saveSession(
                    context = context,
                    userId = user.id,
                    token = null
                )
            }.onFailure { e ->
                _error.value = e.message
            }


        }
    }

}