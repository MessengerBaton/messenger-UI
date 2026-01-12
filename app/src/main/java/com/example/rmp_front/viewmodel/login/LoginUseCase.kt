package com.example.rmp_front.viewmodel.login

import com.example.rmp_front.data.models.User
import com.example.rmp_front.data.repository.AuthRepository


class LoginUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(phone: String, password: String): Result<User> {

        if (phone.isEmpty() || password.isEmpty() || phone.isBlank() || password.isBlank()) {
            return Result.failure(Exception("Please enter your data"))
        }
        else {
            if (!phone.matches(Regex("89\\d{9}\$")) &&
                !phone.matches(Regex("\\+79\\d{9}\$"))
            ) {
                return Result.failure(Exception("Invalid phone format"))
            }
            return try {
                val user = repository.login(phone, password)
                Result.success(user)
            } catch (e: Exception) {
                Result.failure(e)
            }

        }

    }
}
