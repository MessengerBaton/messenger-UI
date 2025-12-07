package com.example.rmp_front.viewmodel.Register

import com.example.rmp_front.data.repository.AuthRepository

class RegisterUseCase (private val repository: AuthRepository) {
    suspend operator fun invoke(phone: String, password: String): Result<Unit> {
        // Валидация телефона (можно добавить пароль)
        if (!phone.matches(Regex("89\\d{9}\$")) &&
            !phone.matches(Regex("\\+79\\d{9}\$"))) {
            return Result.failure(Exception("Invalid phone format"))
        }

        return try {
            repository.register(phone, password)
            Result.success(Unit)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}