//package com.example.rmp_front.data
//
//import com.example.rmp_front.data.dto.*
//import com.example.rmp_front.data.models.User
//import io.ktor.client.*
//import io.ktor.client.engine.android.*
//import io.ktor.client.plugins.contentnegotiation.*
//import io.ktor.client.plugins.logging.*
//import io.ktor.serialization.kotlinx.json.*
//import kotlinx.serialization.json.Json
//import java.text.SimpleDateFormat
//import java.util.*
//import kotlin.collections.mutableListOf
//
//object ServerClient {
//
//    private const val BASE = "http://10.0.2.2:8080/"
//    private val json = Json { ignoreUnknownKeys = true }
//
//    val http = HttpClient(Android) {
//        install(ContentNegotiation) { json(json) }
//        install(Logging) {
//            level = LogLevel.ALL
//            logger = object : Logger {
//                override fun log(message: String) {
//                    android.util.Log.v("KTOR_CLIENT", message)
//                }
//            }
//        }
//    }
//
//    // ===============================
//    // 🔹 IN-MEMORY STORAGE
//    // ===============================
//
//    private const val CURRENT_USER_ID = "1"
//
//
//    private val users = mutableListOf(
//        UserDto(
//            id = "3",
//            nickname = "@super_oleg3000",
//            name = "Oleg",
//            phone = "89888888888",
//            about = null,
//            avatarUrl = null
//        ),
//        UserDto(
//            id = "2",
//            nickname = "@pornodemon",
//            name = "Kirill",
//            phone = "89888888888",
//            about = null,
//            avatarUrl = null
//        ),
//        UserDto(
//            id = "4",
//            nickname = "@privet",
//            name = "Mamont",
//            phone = "89888888888",
//            about = null,
//            avatarUrl = null
//        )
//    )
//
//    private val chats = mutableListOf(
//        ChatDto(
//            id = "44",
//            title = "Mamont",
//            friendId = "4",
//            userId = CURRENT_USER_ID,
//            lastMessage = null,
//            avatarUrl = null
//        )
//
//    )
//    private val messages = mutableListOf<MessageDto>()
//    private val groups = mutableListOf<GroupDto>()
//
//    // ===============================
//    // 🔹 AUTH
//    // ===============================
//
//    suspend fun login(phone: String, password: String): LoginResponse {
//        return LoginResponse(success = true, user = UserDto(
//            id = "1",
//            nickname = "gbhjk",
//            name = "ku",
//            phone = "89888888888",
//            about = null,
//            avatarUrl = null
//
//        ))
//    }
//
//    suspend fun register(phone: String, password: String): RegisterResponse {
//        val user = UserDto(
//            id = UUID.randomUUID().toString(),
//            nickname = "@user_${users.size}",
//            name = "User ${users.size}",
//            phone = phone,
//            about = null,
//            avatarUrl = null
//        )
//        users.add(user)
//        return RegisterResponse(success = true, user = user)
//    }
//
//    // ===============================
//    // 🔹 USERS
//    // ===============================
//
//    suspend fun getUsers(): List<UserDto> = users
//
//    suspend fun getUser(): UserDto = users.first()
//
//    suspend fun getUserById(userId: String): UserDto =
//        users.first { it.id == userId }
//
//    suspend fun updateUser(userDto: UserDto): UserDto {
//        val index = users.indexOfFirst { it.id == userDto.id }
//        if (index != -1) {
//            users[index] = userDto
//        }
//        return userDto
//    }
//
//    // ===============================
//    // 🔹 CHATS
//    // ===============================
//
//    suspend fun createChat(userId: String, user: User): ChatDto {
//        val chat = ChatDto(
//            id = UUID.randomUUID().toString(),
//            friendId = user.id,
//            title = user.name,
//            userId = userId,
//            lastMessage = null,
//            avatarUrl = null
//        )
//        chats.add(chat)
//        return chat
//    }
//
//
//    suspend fun getChats(userId: String): List<ChatDto> {
//        return chats.filter { it.userId == CURRENT_USER_ID }
//    }
//
//
//    suspend fun getChatInfo(chatId: String): ChatInfoDto {
//        val chat = chats.first { it.id == chatId }
//        return ChatInfoDto(
//            id = chat.id,
//            friendId = chat.friendId,
//            title = chat.title,
//            userId = chat.userId,
//            avatarUrl = chat.avatarUrl
//        )
//    }
//
//    // ===============================
//    // 🔹 MESSAGES
//    // ===============================
//
//    suspend fun sendMessage(message: MessageDto): MessageDto {
//        val msg = message.copy(
//            id = UUID.randomUUID().toString(),
//            timestamp = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
//        )
//
//        messages.add(msg)
//
//        val chatIndex = chats.indexOfFirst { it.id == msg.chatId }
//        if (chatIndex != -1) {
//            chats[chatIndex] =
//                chats[chatIndex].copy(lastMessage = msg)
//        }
//
//        return msg
//    }
//
//    suspend fun getChatMessages(chatId: String): MessageListResponse {
//        return MessageListResponse(
//            messages = messages.filter { it.chatId == chatId }
//        )
//    }
//
//    // ===============================
//    // 🔹 GROUPS
//    // ===============================
//
//    suspend fun createGroup(userId: String, chatTitle: String, users: List<UserDto>): GroupDto {
//        val group = GroupDto(
//            id = UUID.randomUUID().toString(),
//            name = chatTitle,
//            members = users,
//            userId = userId,
//            avatarUrl = null,
//            lastMessage = MessageDto("567", "nj", "ya umirayu", SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()), true, )
//        )
//        groups.add(group)
//        return group
//    }
//
//    suspend fun getGroups(userId: String): List<GroupDto> {
//        return groups.filter { it.userId == CURRENT_USER_ID }
//    }
//
//
//    suspend fun getGroupById(groupId: String): GroupDto =
//        groups.first { it.id == groupId }
//}
//
//





package com.example.rmp_front.data

import android.os.Build.VERSION_CODES.BASE
import com.example.rmp_front.data.ServerClient.http
import com.example.rmp_front.data.dto.*
import com.example.rmp_front.data.models.User
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.mutableListOf

object ServerClient {

    private const val BASE = "http://10.0.2.2:8080/"
    private val json = Json { ignoreUnknownKeys = true }

    val http = HttpClient(Android) {
        install(ContentNegotiation) { json(json) }
        install(Logging) {
            level = LogLevel.ALL
            logger = object : Logger {
                override fun log(message: String) {
                    android.util.Log.v("KTOR_CLIENT", message)
                }
            }
        }
    }

    // ===============================
    // 🔹 IN-MEMORY STORAGE
    // ===============================

    private const val CURRENT_USER_ID = "11111111-1111-1111-1111-111111111111"


    private val users = mutableListOf(
        UserDto(
            id = "3",
            nickname = "@super_oleg3000",
            name = "Oleg",
            phone = "89888888888",
            about = null,
            avatarUrl = null
        ),
        UserDto(
            id = "2",
            nickname = "@pornodemon",
            name = "Kirill",
            phone = "89888888888",
            about = null,
            avatarUrl = null
        ),
        UserDto(
            id = "4",
            nickname = "@privet",
            name = "Mamont",
            phone = "89888888888",
            about = null,
            avatarUrl = null
        )
    )

    private val chats = mutableListOf(
        ChatDto(
            id = "44",
            title = "Mamont",
            friendId = "4",
            userId = CURRENT_USER_ID,
            lastMessage = null,
            avatarUrl = null
        )

    )
    private val messages = mutableListOf<MessageDto>()
    private val groups = mutableListOf<GroupDto>()

    // ===============================
    // 🔹 AUTH
    // ===============================

    suspend fun login(userId: String, password: String): UserDto {
        return http.get("$BASE/user/${userId}?password=${password}").body()
    }

    suspend fun register(user: UserDto): UserDto {
        return http.post("$BASE/register") {
            setBody(user)
        }.body()
    }



    // ===============================
    // 🔹 USERS
    // ===============================

    suspend fun getUsers(): List<UserDto> {
        return http.get("$BASE/users").body()
    }

    suspend fun getUser(): UserDto = users.first()

    suspend fun getUserById(id: String): UserDto {
        return http.get("${BASE}/user/$id").body()
    }

    suspend fun updateUser(userDto: UserDto): UserDto {
        return http.patch("${BASE}/user/${userDto.id}").body()
    }

    // ===============================
    // 🔹 CHATS
    // ===============================

    suspend fun createChat(userId: String, user: User): ChatDto {
        val chat = ChatDto(
            id = UUID.randomUUID().toString(),
            friendId = user.id,
            title = user.name,
            userId = userId,
            lastMessage = null,
            avatarUrl = null
        )

        return http.post("${BASE}/chat/create/private") {
            setBody(chat)
        }.body()
    }


    suspend fun getChats(userId: String): List<ChatDto> {
        return http.get("${BASE}/chats/$userId/with/private").body()
    }

    suspend fun getChatInfo(chatId: String): ChatInfoDto {
        val chat = http.get("${BASE}/chat/$chatId").body<ChatDto>()
        return ChatInfoDto(
            id = chat.id,
            friendId = chat.friendId,
            title = chat.title,
            userId = chat.userId,
            avatarUrl = chat.avatarUrl
        )
    }

    // ===============================
    // 🔹 MESSAGES
    // ===============================

    suspend fun sendMessage(userId: String, message: MessageDto): MessageDto {
        return http.post("${BASE}/message/${message.chatId}/by/${userId}") {
            setBody(message)
        }.body()
    }

    suspend fun getChatMessages(userId: String, chatId: String): MessageListResponse {
        return http.get("${BASE}/chat/$chatId/messages?user_id=${userId}").body()
    }

    // ===============================
    // 🔹 GROUPS
    // ===============================

    suspend fun createGroup(userId: String, chatTitle: String, users: List<UserDto>): GroupDto {
        val group = GroupDto(
            id = UUID.randomUUID().toString(),
            name = chatTitle,
            members = users,
            userId = userId,
            avatarUrl = null,
            lastMessage = null
        )

        return http.post("${BASE}/chat/create/group") {
            setBody(group)
        }.body()
    }

    suspend fun getGroups(userId: String): List<GroupDto> {
        return http.get("${BASE}/chats/$userId/with/group").body()
    }


    suspend fun getGroupById(groupId: String): GroupDto {
        return http.get("${BASE}/chat/$groupId").body()
    }
}