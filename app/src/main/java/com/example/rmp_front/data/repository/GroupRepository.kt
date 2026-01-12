package com.example.rmp_front.data.repository

import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.dto.GroupDto

class GroupRepository(private val api: ServerClient) {

//    suspend fun getGroup(): GroupDto {
//        return api.getGroup()
//    }

    suspend fun getGroupById(id: String): GroupDto {
        return api.getGroupById(id)
    }

}