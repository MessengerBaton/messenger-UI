package com.example.rmp_front.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.models.User
import com.example.rmp_front.data.repository.UserRepository
import com.example.rmp_front.viewmodel.user.UserUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val userRepository = UserRepository(ServerClient)
    private val userUseCase = UserUseCase(userRepository)


    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun updateUser(newUser: User) {
        _user.value = newUser
    }

    fun loadUser() {
        viewModelScope.launch {
            val user = async { userUseCase.getUser() }
            val userResult = user.await()

            userResult.onSuccess {
                _user.value = it
            }.onFailure {
                _error.value =  it.message ?: "Unknown error"
            }
        }
    }

}