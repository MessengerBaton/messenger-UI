package com.example.rmp_front.viewmodel.user

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.models.User
import com.example.rmp_front.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChangeProfileViewModel : ViewModel() {

    private val repository = UserRepository(ServerClient)
    private val userUseCase = UserUseCase(repository)

    private val _user = MutableStateFlow(User(
        id = "user_1",
        name = "Kitty",
        nick = "@super_kitty",
        phone = "89222659356",
        about = "hi i'm Kitty",
        avatarUrl = ""
    ))
    val user: StateFlow<User> = _user

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    private val _updateSuccess = MutableStateFlow(false)
    val updateSuccess: StateFlow<Boolean> = _updateSuccess


    fun updateUser(user: User) {
        viewModelScope.launch {
            val result = userUseCase.updateUser(user)

            result.onSuccess {
                _user.value = it
                _updateSuccess.value = true
            }.onFailure {
                _error.value =  it.message ?: "Unknown error with updating profile"
            }
        }
    }
}
