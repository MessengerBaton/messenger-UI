package com.example.rmp_front.viewmodel.group


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.models.Group
import com.example.rmp_front.data.repository.GroupRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GroupViewModel : ViewModel() {

    private val repository = GroupRepository(ServerClient)
    private val groupUseCase = GroupUseCase(repository)

    private val _group = MutableStateFlow<Group?>(null)
    val group: StateFlow<Group?> = _group

    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error

    fun loadGroup(id: String) {
        viewModelScope.launch {
            val result = groupUseCase(id)

            result.onSuccess {
                _group.value = it
            }.onFailure {
                _error.value =  it.message ?: "Unknown error"
            }
        }
    }
}
