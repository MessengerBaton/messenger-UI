package com.example.rmp_front.viewmodel.chat

import com.example.rmp_front.data.dto.MessageDto
import com.example.rmp_front.data.dto.toDomain
import com.example.rmp_front.data.models.Chat
import com.example.rmp_front.data.models.Message
import com.example.rmp_front.data.repository.ChatRepository

class ChatUseCase(
    private val repository: ChatRepository
) {
    suspend fun getChatMessages(chatId: String): Result<List<Message>> {
        return try {
            val dto = repository.getChatMessages(chatId)   // DTO
            val chats = dto.messages.map { it.toDomain() }
            Result.success(chats)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun getChatInfo(chatId: String): Result<Chat> {
        return try {
            val dto = repository.getChatInfo(chatId)
            Result.success(dto.toDomain())

        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun sendMessage(message: Message): Result<Message> {
        return try {
            val msg = repository.sendMessage(message)
            Result.success(msg)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
