package com.example.rmp_front.data.repository

import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.dto.GroupDto
import com.example.rmp_front.data.dto.UserDto

class GroupRepository(private val api: ServerClient) {

    suspend fun getGroups(userId: String): List<GroupDto> {
        return api.getGroups(userId)
    }

    suspend fun getGroupById(id: String): GroupDto {
        return api.getGroupById(id)
    }


    suspend fun createGroup(chatTitle: String, users: List<UserDto>): GroupDto {
        return api.createGroup(chatTitle, users)
    }

}