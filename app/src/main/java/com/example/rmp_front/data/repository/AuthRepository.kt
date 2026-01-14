package com.example.rmp_front.data.repository

import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.dto.toDomain
import com.example.rmp_front.data.models.User
import com.example.rmp_front.data.models.toDto

class AuthRepository (private val api: ServerClient) {

    suspend fun login(phone: String, password: String): User{
        val resp = api.login(phone, password)
        if (!resp.success) {
            throw Exception(resp.message ?: "Unknown error")
        }
        return resp.user!!.toDomain()
    }

    suspend fun register(phone: String, password: String): User {
        val resp = api.register(phone, password)
        if (!resp.success){
            throw Exception(resp.message ?: "Unknown error")
        }
        return resp.user!!.toDomain()
    }
}

