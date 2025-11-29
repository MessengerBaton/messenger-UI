package com.example.rmp_front.viewmodel.Login

import com.example.rmp_front.data.repository.AuthRepository


class LoginUseCase(private val repository: AuthRepository) {

    suspend operator fun invoke(phone: String, password: String): Result<Unit> {
        if (!phone.matches(Regex("89\\d{9}\$")) &&
            !phone.matches(Regex("\\+79\\d{9}\$"))) {
            return Result.failure(Exception("Invalid phone format"))
        }

        return try{
            repository.login(phone, password)
            Result.success(Unit)
        } catch (e: Exception){
            Result.failure(e)
        }

    }
}
