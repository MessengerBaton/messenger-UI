package com.example.rmp_front.data.repository

import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.data.dto.ChatCreationDto
import com.example.rmp_front.data.dto.ChatInfoDto
import com.example.rmp_front.data.models.Chat
import com.example.rmp_front.data.dto.ChatListResponse
import com.example.rmp_front.data.dto.MessageListResponse
import com.example.rmp_front.data.dto.toDomain
import com.example.rmp_front.data.models.Message
import com.example.rmp_front.data.models.toDto

class ChatRepository(private val api: ServerClient) {

    suspend fun getChats(userId : String): ChatListResponse {
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

    suspend fun createChat(userIds: List<String>, title: String?): Chat {
        val dto = ChatCreationDto(
            userIds  = userIds,
            title = title,
            isGroup = userIds.size > 1
        )
        return TODO("Provide the return value")
    }



}
