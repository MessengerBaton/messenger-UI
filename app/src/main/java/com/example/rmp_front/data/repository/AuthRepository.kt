package com.example.rmp_front.data.repository

import com.example.rmp_front.data.LoginRequest


import com.example.rmp_front.data.ServerClient

class AuthRepository (private val api: ServerClient) {

    suspend fun login(phone: String, password: String){
        val resp = api.login(phone, password)
        if (!resp.success) throw Exception(resp.message ?: "Unknown error")

    }

    suspend fun register(phone: String, password: String) {
//        val resp = ServerClient.register(phone, password)
//        if (!resp.success) throw Exception(resp.error ?: "Unknown error")
    }
}

