package com.example.rmp_front.viewmodel.chatCreation


import com.example.rmp_front.data.dto.toDomain
import com.example.rmp_front.data.models.Chat
import com.example.rmp_front.data.models.Group
import com.example.rmp_front.data.models.User
import com.example.rmp_front.data.models.toDto
import com.example.rmp_front.data.repository.ChatRepository
import com.example.rmp_front.data.repository.GroupRepository
import com.example.rmp_front.data.repository.UserRepository

class ChatCreationUseCase(
    private val userRepository: UserRepository,
    private val groupRepository: GroupRepository,
    private val chatRepository: ChatRepository
) {
    suspend fun createGroup(userId: String, chatTitle: String, users: List<User>): Result<Group> {
        return try {
            val usersDto = users.map { user -> user.toDto() }
            val dto = groupRepository.createGroup(userId, chatTitle, usersDto)
            Result.success(dto.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun createChat(userId: String, user: User): Result<Chat> {
        return try {
            val dto = chatRepository.createChat(userId, user)
            Result.success(dto.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


    suspend fun getUsers(): Result<List<User>> {
        return try {
            val dto = userRepository.getUsers()   // DTO
            Result.success(dto.map { it.toDomain() })
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}