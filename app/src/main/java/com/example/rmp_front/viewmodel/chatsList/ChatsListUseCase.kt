package com.example.rmp_front.viewmodel.chatsList

import com.example.rmp_front.data.dto.toDomain
import com.example.rmp_front.data.models.Chat
import com.example.rmp_front.data.models.Group
import com.example.rmp_front.data.repository.ChatRepository
import com.example.rmp_front.data.repository.GroupRepository

class ChatsListUseCase(
    private val chatRepository: ChatRepository,
    private val groupRepository: GroupRepository
) {
    suspend fun loadChats(userId: String): Result<List<Chat>> {
        return try {
            val dto = chatRepository.getChats(userId)
            val chats = dto.map { it.toDomain() }
            Result.success(chats)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun loadGroups(userId: String): Result<List<Group>> {
        return try {
            val dto = groupRepository.getGroups(userId)
            val groups = dto.map { it.toDomain() }
            Result.success(groups)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
