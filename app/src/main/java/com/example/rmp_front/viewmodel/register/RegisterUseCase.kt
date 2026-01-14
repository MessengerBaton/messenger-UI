package com.example.rmp_front.viewmodel.register


import com.example.rmp_front.data.models.User
import com.example.rmp_front.data.repository.AuthRepository


class RegisterUseCase(private val repository: AuthRepository) {

    fun validatePhone(phone: String): Result<Unit>{
        if (phone.isBlank())
            return Result.failure(Exception("Please enter phone number"))

        if (!phone.matches(Regex("89\\d{9}\$")) &&
            !phone.matches(Regex("\\+79\\d{9}\$"))
        )
            return Result.failure(Exception("Invalid phone format"))

        return Result.success(Unit)
    }

    suspend operator fun invoke(phone: String, password: String): Result<User> {
        if (password.isBlank()) {
            return Result.failure(Exception("Please enter password"))
        }

        return try {
            val user = repository.register(phone, password)
            Result.success(user)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
