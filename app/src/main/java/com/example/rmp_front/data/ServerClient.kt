package com.example.rmp_front.data

import com.example.rmp_front.data.dto.ChatDto
import com.example.rmp_front.data.dto.ChatInfoDto
import com.example.rmp_front.data.dto.ChatListResponse
import com.example.rmp_front.data.dto.LoginResponse
import com.example.rmp_front.data.dto.MessageDto
import com.example.rmp_front.data.dto.MessageListResponse
import com.example.rmp_front.data.dto.RegisterResponse
import com.example.rmp_front.data.dto.UserDto
import com.example.rmp_front.data.models.Chat
import com.example.rmp_front.data.models.Message
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json


import io.ktor.client.plugins.logging.*
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.UUID

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

    suspend fun getTemp(): List<Message> {
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


    // пока заглушка
    suspend fun getChats(): ChatListResponse {
        return ChatListResponse(
            chats = listOf(
                ChatDto(
                    id = "1",
                    title = "Murrr",
                    userId = "2",
                    lastMessage = MessageDto(
                        id = "m1",
                        chatId = "1",
                        isFromMe = true,
                        text = "Привет!",
                        timestamp = "${SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())}"
                    ),
                    avatarUrl = null
                )
            )
        )
    }



    suspend fun getChatMessages(chatId: String): MessageListResponse {
        return MessageListResponse(
            messages = listOf(
                MessageDto(
                    id = "1",
                    chatId = chatId,
                    isFromMe = true,
                    text = "Привет! Это тестовое сообщение",
                    timestamp = "${SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())}"
                ),
                MessageDto(
                    id = "2",
                    chatId = chatId,
                    isFromMe = false,
                    text = "Здарова, всё работает!",
                    timestamp = "${SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())}"
                )
            )
        )
    }

    suspend fun sendMessage(message: MessageDto): MessageDto {
        return message.copy(
            id = message.id.ifEmpty { UUID.randomUUID().toString() },
            timestamp = message.timestamp.ifEmpty { SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()) }
        )
    }

    suspend fun getChatInfo(chatId: String): ChatInfoDto {
        return ChatInfoDto(
            id = chatId,
            userId = "2",
            title = "Murrr",
            avatarUrl = null
        )
    }


    suspend fun getUser(): UserDto {
        return UserDto(
            id = "1",
            nickname = "@super_kitty",
            name = "Kitty",
            phone = "89888888888",
            about = null,
            avatarUrl = null
        )
    }


    suspend fun getUserById(id: String): UserDto {
        return UserDto(
            id = "2",
            nickname = "@mimi_mi",
            name = "Murrr",
            phone = "89222222222",
            about = "hi i'm kitty",
            avatarUrl = null
        )
    }


    suspend fun updateUser(userDto: UserDto): UserDto {
        return UserDto(
            id = "1",
            nickname = "@mega_kitty",
            name = "Kitty",
            phone = "89888888888",
            about = null,
            avatarUrl = null
        )
    }

}
