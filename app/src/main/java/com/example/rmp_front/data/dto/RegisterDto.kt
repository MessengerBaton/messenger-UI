package com.example.rmp_front.data.dto

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class RegisterRequest(
    val phone: String,
    val password: String
)
@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class RegisterResponse(
    val success: Boolean,
    val token: String ? = null,
    val message: String? = null,
    val user: UserDto ? = null
)
