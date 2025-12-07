package com.example.rmp_front.data

@kotlinx.serialization.Serializable
data class RegisterRequest(
    val phone: String,
    val password: String
)

@kotlinx.serialization.Serializable
data class AuthResponse(
    val success: Boolean,
    val message: String?
)
