package com.example.rmp_front.viewmodel.group

import com.example.rmp_front.data.dto.toDomain
import com.example.rmp_front.data.models.Group
import com.example.rmp_front.data.repository.GroupRepository


class GroupUseCase(
    private val repository: GroupRepository
) {

    suspend operator fun invoke(id: String): Result<Group> {
        return try {
            val dto = repository.getGroupById(id)
            Result.success(dto.toDomain())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
