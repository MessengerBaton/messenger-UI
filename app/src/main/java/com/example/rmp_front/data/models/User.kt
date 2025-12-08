package com.example.rmp_front.data.models

import com.example.rmp_front.data.dto.MessageDto
import com.example.rmp_front.data.dto.UserDto

data class User(
    val id: String,
    val nick: String,
    val name: String,
    val phone: String,
    val about: String?,
    val avatarUrl: String?
)


fun User.toDto() = UserDto (
    id = id,
    nickname = nick,
    name = name,
    phone = phone,
    about = about,
    avatarUrl = avatarUrl
)