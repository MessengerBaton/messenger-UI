package com.example.rmp_front.data

import com.example.rmp_front.data.models.Message
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("posts")
    suspend fun sendMessage(@Body message: Message): Response<Message>
}