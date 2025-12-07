package com.example.rmp_front.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: String,
    val nickname: String,
    val name: String,
    val phone: String,
    val about: String?,
    val avatarUrl: String?
)


