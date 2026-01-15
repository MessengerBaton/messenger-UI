package com.example.rmp_front.data.models

import com.example.rmp_front.data.dto.GroupDto

data class Group(
    val userId: String,
    val id: String,
    val name: String,
    val members: List<User>,
    val lastMessage: String? = null,
    val timestamp: String? = null,
    val avatarUrl: String? = null,
)


//fun Group.toDto() = GroupDto(
//    id = id,
//    name = name,
//    members = members
//)