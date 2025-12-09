package com.example.rmp_front.viewmodel.chatsList

import com.example.rmp_front.data.dto.toDomain
import com.example.rmp_front.data.models.Chat
import com.example.rmp_front.data.repository.ChatRepository

class ChatsListUseCase(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(): Result<List<Chat>> {
        return try {
            val dto = repository.getChats()   // DTO
            val chats = dto.chats.map { it.toDomain() }
            Result.success(chats)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
