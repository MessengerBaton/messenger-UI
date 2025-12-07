package com.example.rmp_front.data

import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


import io.ktor.client.plugins.logging.*

object ServerClient {
    // Подставь сюда адрес своего сервера:
    // - при эмуляторе: "http://10.0.2.2:8080/"
    // - при физическом устройстве: "http://192.168.x.y:8080/"
    private const val BASE = "http://10.0.2.2:8080/"

    private val json = Json { ignoreUnknownKeys = true }

    val http = HttpClient(Android) {
        install(ContentNegotiation) {
            json(json)
        }

        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    // логи в Logcat Android
                    android.util.Log.v("KTOR_CLIENT", message)
                }
            }
        }

        engine {
            connectTimeout = 10_000
            socketTimeout = 10_000
        }
    }

    suspend fun getTemp(): List<MessageModel> {
        return http.get("${BASE}/data-test").body()
    }

    suspend fun login(phone: String, password: String): LoginResponse {
//        return http.post("${BASE}/login") {
//            setBody(LoginRequest(phone, password))
//        }.body()
        return LoginResponse(
            success = true,
        )
    }

    suspend fun register(phone: String, password: String): RegisterResponse {
        return RegisterResponse(
            success = true,
        )
    }
}
