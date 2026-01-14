package com.example.rmp_front.data.dto

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable
@SuppressLint("UnsafeOptInUsageError")

@Serializable
data class LoginRequest(
    val phone: String,
    val password: String
)
@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class LoginResponse(
    val success: Boolean,
    val token: String ? = null,
    val message: String? = null,
    val user: UserDto ? = null
)
