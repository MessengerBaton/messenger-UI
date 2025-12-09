package com.example.rmp_front.viewmodel.friend

import com.example.rmp_front.data.dto.toDomain
import com.example.rmp_front.data.models.User
import com.example.rmp_front.data.repository.UserRepository

class FriendUseCase(
    private val repository: UserRepository
) {

    suspend operator fun invoke(id: String): Result<User> {
        return try {
            val dto = repository.getUserById(id)
            Result.success(dto.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
