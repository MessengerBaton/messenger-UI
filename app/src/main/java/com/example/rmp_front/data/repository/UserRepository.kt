package com.example.rmp_front.data.repository

import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.dto.UserDto
import com.example.rmp_front.data.dto.toDomain
import com.example.rmp_front.data.models.Chat
import com.example.rmp_front.data.models.User
import com.example.rmp_front.data.models.toDto

class UserRepository(private val api: ServerClient) {

    suspend fun getUser(): UserDto {
        return api.getUser()
    }

    suspend fun getUserById(id: String): UserDto {
        return api.getUserById(id)
    }

    suspend fun updateUser(user: User): User {
        val dto = user.toDto()
        api.updateUser(dto)
        return dto.toDomain()
    }

    suspend fun getUsers(): List<UserDto> {
        return api.getUsers()
    }
}