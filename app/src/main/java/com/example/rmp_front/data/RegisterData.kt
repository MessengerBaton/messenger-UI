package com.example.rmp_front.data

import kotlinx.serialization.Serializable

@Serializable
data class RegisterRequest(
    val phone: String,
    val password: String
)

@Serializable
data class RegisterResponse(
    val success: Boolean,
    val token: String? = null,
    val message: String? = null
)
