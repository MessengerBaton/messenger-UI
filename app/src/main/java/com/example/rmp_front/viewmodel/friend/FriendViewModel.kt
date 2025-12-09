package com.example.rmp_front.viewmodel.friend

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.models.User
import com.example.rmp_front.data.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class FriendViewModel : ViewModel() {

    private val repository = UserRepository(ServerClient)
    private val friendUseCase = FriendUseCase(repository)

    private val _friend = MutableStateFlow<User?>(null)
    val friend: StateFlow<User?> = _friend

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadFriend(id: String) {
        viewModelScope.launch {
            val result = friendUseCase(id)

            result.onSuccess {
                _friend.value = it
            }.onFailure {
                _error.value =  it.message ?: "Unknown error"
            }
        }
    }
}
