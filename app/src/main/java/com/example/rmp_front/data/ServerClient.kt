//package com.example.rmp_front.data
//
//import androidx.compose.ui.graphics.vector.Group
//import com.example.rmp_front.data.dto.ChatDto
//import com.example.rmp_front.data.dto.ChatInfoDto
//import com.example.rmp_front.data.dto.ChatListResponse
//import com.example.rmp_front.data.dto.GroupDto
//import com.example.rmp_front.data.dto.LoginResponse
//import com.example.rmp_front.data.dto.MessageDto
//import com.example.rmp_front.data.dto.MessageListResponse
//import com.example.rmp_front.data.dto.RegisterResponse
//import com.example.rmp_front.data.dto.UserDto
//import com.example.rmp_front.data.models.Chat
//import com.example.rmp_front.data.models.Message
//import com.example.rmp_front.data.models.User
//import io.ktor.client.*
//import io.ktor.client.call.body
//import io.ktor.client.engine.android.*
//import io.ktor.client.plugins.contentnegotiation.*
//import io.ktor.client.request.*
//import io.ktor.serialization.kotlinx.json.*
//import kotlinx.serialization.json.Json
//
//
//import io.ktor.client.plugins.logging.*
//import java.text.SimpleDateFormat
//import java.util.Date
//import java.util.Locale
//import java.util.UUID
//import kotlin.String
//
//object ServerClient {
//    // Подставь сюда адрес своего сервера:
//    // - при эмуляторе: "http://10.0.2.2:8080/"
//    // - при физическом устройстве: "http://192.168.x.y:8080/"
//    private const val BASE = "http://10.0.2.2:8080/"
//
//    private val json = Json { ignoreUnknownKeys = true }
//
//    val http = HttpClient(Android) {
//        install(ContentNegotiation) {
//            json(json)
//        }
//
//        install(Logging) {
//            level = LogLevel.ALL
//            logger = object : Logger {
//                override fun log(message: String) {
//                    // логи в Logcat Android
//                    android.util.Log.v("KTOR_CLIENT", message)
//                }
//            }
//        }
//
//        engine {
//            connectTimeout = 10_000
//            socketTimeout = 10_000
//        }
//    }
//
////    suspend fun getTemp(): List<Message> {
////        return http.get("${BASE}/data-test").body()
////    }
//
//    suspend fun login(phone: String, password: String): LoginResponse {
////        return http.post("${BASE}/login") {
////            setBody(LoginRequest(phone, password))
////        }.body()
//        return LoginResponse(
//            success = true,
//            user = UserDto(
//                id = "1",
//                nickname = "Kitty",
//                name = "Kitty",
//                phone = phone,
//                about = null,
//                avatarUrl = null
//            )
//        )
//    }
//
//    suspend fun register(phone: String, password: String): RegisterResponse {
//        return RegisterResponse(
//            success = true,
//            user = UserDto(
//                id = "sdfgthyujikol",
//                nickname = "Kitty",
//                name = "Kitty",
//                phone = phone,
//                about = null,
//                avatarUrl = null
//            )
//        )
//    }
//
//
//    // пока заглушка
//    suspend fun getChats(userId: String): ChatListResponse {
//        return ChatListResponse(
//            chats = listOf(
//                ChatDto(
//                    id = "1",
//                    title = "Murrr",
//                    userId = "2",
//                    lastMessage = MessageDto(
//                        id = "m1",
//                        chatId = "1",
//                        isFromMe = true,
//                        text = "Привет!",
//                        timestamp = "${SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())}"
//                    ),
//                    avatarUrl = null
//                )
//            )
//        )
//    }
//
//
//
//    suspend fun getChatMessages(chatId: String): MessageListResponse {
//        return MessageListResponse(
//            messages = listOf(
//                MessageDto(
//                    id = "1",
//                    chatId = chatId,
//                    isFromMe = true,
//                    text = "Привет! Это тестовое сообщение",
//                    timestamp = "${SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())}"
//                ),
//                MessageDto(
//                    id = "2",
//                    chatId = chatId,
//                    isFromMe = false,
//                    text = "Здарова, всё работает!",
//                    timestamp = "${SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())}"
//                )
//            )
//        )
//    }
//
//    suspend fun sendMessage(message: MessageDto): MessageDto {
//        return message.copy(
//            id = message.id.ifEmpty { UUID.randomUUID().toString() },
//            timestamp = message.timestamp.ifEmpty { SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date()) }
//        )
//    }
//
//    suspend fun getChatInfo(chatId: String): ChatInfoDto {
//        return ChatInfoDto(
//            id = chatId,
//            userId = "2",
//            title = "Murrr",
//            avatarUrl = null
//        )
//    }
//
//
//    suspend fun getUsers(): List<UserDto> {
//        return listOf(
//            UserDto(
//                id = "1",
//                nickname = "@super_oleg3000",
//                name = "Oleg",
//                phone = "89888888888",
//                about = null,
//                avatarUrl = null
//            ),
//            UserDto(
//                id = "3",
//                nickname = "@pornodemon",
//                name = "Kirill",
//                phone = "89888888888",
//                about = null,
//                avatarUrl = null
//            )
//        )
//
//    }
//
//
//    suspend fun getUser(): UserDto {
//        return UserDto(
//            id = "1",
//            nickname = "@super_kitty",
//            name = "Kitty",
//            phone = "89888888888",
//            about = null,
//            avatarUrl = null
//        )
//    }
//
//
//    suspend fun getUserById(id: String): UserDto {
//        return UserDto(
//            id = "2",
//            nickname = "@mimi_mi",
//            name = "Murrr",
//            phone = "89222222222",
//            about = "hi i'm kitty",
//            avatarUrl = null
//        )
//    }
//
//
//    suspend fun updateUser(userDto: UserDto): UserDto {
//        return UserDto(
//            id = "1",
//            nickname = "@mega_kitty",
//            name = "Kitty",
//            phone = "89888888888",
//            about = null,
//            avatarUrl = null
//        )
//    }
//
//
//    suspend fun getGroups(userId: String): List<GroupDto> {
//        return listOf(
//            GroupDto(
//                id = "2",
//                userId = "2",
//                name = "Kitty Gang",
//                members = listOf(
//                    UserDto(
//                        id = "1",
//                        nickname = "Murrr",
//                        name = "Kitty",
//                        phone = "89888888888",
//                        about = null,
//                        avatarUrl = null,
//                    ),
//                    UserDto(
//                        id = "2",
//                        nickname = "Meow",
//                        name = "Meow",
//                        phone = "89888888888",
//                        about = null,
//                        avatarUrl = null
//                    )
//                )
//            ),
//            GroupDto(
//                id = "5",
//                userId = "2",
//                name = "Black List )))",
//                members = listOf(
//                    UserDto(
//                        id = "1",
//                        nickname = "Murrr",
//                        name = "Kitty",
//                        phone = "89888888888",
//                        about = null,
//                        avatarUrl = null,
//                    ),
//                    UserDto(
//                        id = "2",
//                        nickname = "Meow",
//                        name = "Meow",
//                        phone = "89888888888",
//                        about = null,
//                        avatarUrl = null
//                    )
//                )
//            )
//        )
//    }
//
//
//    suspend fun getGroupById(groupId: String): GroupDto {
//        return GroupDto(
//            id = "2",
//            userId = "2",
//            name = "Kitty Gang",
//            members = listOf(
//                UserDto(
//                    id = "1",
//                    nickname = "Murrr",
//                    name = "Kitty",
//                    phone = "89888888888",
//                    about = null,
//                    avatarUrl = null,
//                ),
//                UserDto(
//                    id = "2",
//                    nickname = "Meow",
//                    name = "Meow",
//                    phone = "89888888888",
//                    about = null,
//                    avatarUrl = null
//                )
//            )
//        )
//    }
//
//    suspend fun createGroup(chatTitle: String, users: List<UserDto>): GroupDto {
//        return GroupDto(
//            id = "567",
//            members = users,
//            name = chatTitle,
//            userId = "2"
//        )
//    }
//
//    suspend fun createChat( user: User): ChatDto {
//        return ChatDto(
//            id = user.id,
//            title = user.name,
//            lastMessage = null,
//            avatarUrl = null,
//            userId = "2"
//        )
//    }
//
//}



package com.example.rmp_front.data

import com.example.rmp_front.data.dto.*
import com.example.rmp_front.data.models.User
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.get
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

    suspend fun login(phone: String, password: String): LoginResponse {
        return LoginResponse(success = true, user = UserDto(
            id = "1",
            nickname = "gbhjk",
            name = "ku",
            phone = "89888888888",
            about = null,
            avatarUrl = null

        ))
    }

    suspend fun register(phone: String, password: String): RegisterResponse {
        val user = UserDto(
            id = UUID.randomUUID().toString(),
            nickname = "@user_${users.size}",
            name = "User ${users.size}",
            phone = phone,
            about = null,
            avatarUrl = null
        )
        users.add(user)
        return RegisterResponse(success = true, user = user)
    }

    // ===============================
    // 🔹 USERS
    // ===============================

    suspend fun getUsers(): List<UserDto> {
        return http.get("${BASE}/users").body()
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
        println(http.get("${BASE}/chats/$userId/with/private").body<List<ChatDto>>())
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

    suspend fun sendMessage(message: MessageDto): MessageDto {
        return http.post("${BASE}/message/${message.chatId}/by/${CURRENT_USER_ID}") {
            setBody(message)
        }.body()
    }

    suspend fun getChatMessages(chatId: String): MessageListResponse {
        return http.get("${BASE}/chat/$chatId/messages?user_id=$CURRENT_USER_ID").body()
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
