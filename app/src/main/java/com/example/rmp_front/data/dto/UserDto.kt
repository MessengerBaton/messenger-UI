package com.example.rmp_front.data.dto

import android.annotation.SuppressLint
import com.example.rmp_front.data.models.User
import kotlinx.serialization.Serializable
@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class UserDto(
    val id: String,
    val nickname: String,
    val name: String,
    val phone: String,
    val about: String?,
    val avatarUrl: String?
)


fun UserDto.toDomain() = User(
    id = id,
    name = name,
    nick = nickname,
    phone = phone,
    about = about,
    avatarUrl = avatarUrl
)
