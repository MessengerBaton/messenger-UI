package com.example.rmp_front.data.dto

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable
@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class ChatCreationDto(
    val userIds: List<String>,
    val title: String? = null,
    val isGroup: Boolean = false
)