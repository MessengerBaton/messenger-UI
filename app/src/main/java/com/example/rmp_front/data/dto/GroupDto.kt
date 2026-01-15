package com.example.rmp_front.data.dto


import com.example.rmp_front.data.models.Group
import kotlinx.serialization.Serializable

@Serializable
data class GroupDto(
    val userId: String,
    val id: String,
    val name: String,
    val members: List<UserDto>,
    val lastMessage: MessageDto?,
    val avatarUrl: String?,
)


fun GroupDto.toDomain() = Group(
    userId = userId,
    id = id,
    name = name,
    members = members.map { it.toDomain() },
    lastMessage = lastMessage?.toDomain()?.text,
    timestamp = lastMessage?.toDomain()?.timestamp,
)
