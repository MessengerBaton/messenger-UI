package com.example.rmp_front.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequest(
    val phone: String,
    val password: String
)

@Serializable
data class LoginResponse(
    val success: Boolean,
    val token: String ? = null,
    val message: String? = null,
    val user: UserDto ? = null
)
