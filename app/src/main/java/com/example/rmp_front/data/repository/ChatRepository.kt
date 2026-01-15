package com.example.rmp_front.data.repository

import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.dto.ChatDto
import com.example.rmp_front.data.dto.ChatInfoDto
import com.example.rmp_front.data.dto.MessageListResponse
import com.example.rmp_front.data.dto.toDomain
import com.example.rmp_front.data.models.Chat
import com.example.rmp_front.data.models.Message
import com.example.rmp_front.data.models.User
import com.example.rmp_front.data.models.toDto
import java.util.UUID

class ChatRepository(private val api: ServerClient) {

    suspend fun getChats(userId : String): List<ChatDto> {
        return api.getChats(userId)
    }


    suspend fun getChatMessages(chatId: String): MessageListResponse {
        return api.getChatMessages(chatId)
    }


    suspend fun getChatInfo(chatId: String): ChatInfoDto {
        return api.getChatInfo(chatId)
    }


    suspend fun sendMessage(message: Message): Message {
        val dto = message.toDto()
        api.sendMessage(dto)
        return dto.toDomain()
    }


    suspend fun createChat(userId: String, user: User): ChatDto {
        return api.createChat(userId, user)
    }

}
