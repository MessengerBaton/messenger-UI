package com.example.rmp_front.data.repository

import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.dto.ChatListResponse

class ChatRepository(private val api: ServerClient) {

    suspend fun getChats(): ChatListResponse {
        return api.getChats()
    }
}
