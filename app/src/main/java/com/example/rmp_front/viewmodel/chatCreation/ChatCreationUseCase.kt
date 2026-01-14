package com.example.rmp_front.viewmodel.chatCreation

import com.example.rmp_front.data.models.Chat
import com.example.rmp_front.data.repository.ChatRepository

class ChatCreationUseCase(
    private val repository: ChatRepository
) {
    suspend operator fun invoke(userIds: List<String>, title: String? = null): Result<Chat> {
        return try {
            // В реальном приложении здесь будет вызов repository.createChat()
            // Пока возвращаем заглушку
            val chat = Chat(
                id = "chat_${System.currentTimeMillis()}",
                userId = userIds.first(),
                title = title ?: "New Chat",
                lastMessage = "",
                timestamp = "",
                avatarUrl = null
            )
            Result.success(chat)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
