package com.example.rmp_front.viewmodel.user

import com.example.rmp_front.data.dto.UserDto
import com.example.rmp_front.data.dto.toDomain
import com.example.rmp_front.data.models.User
import com.example.rmp_front.data.repository.UserRepository

class UserUseCase(
    private val repository: UserRepository
) {
    suspend fun getUser(): Result<User> {
        return try {
            val dto = repository.getUser()   // DTO
            Result.success(dto.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun updateUser(user: User): Result<User> {
        return try{
            Result.success(repository.updateUser(user))
        } catch (e: Exception) {
            Result.failure(e)
        }

    }
}