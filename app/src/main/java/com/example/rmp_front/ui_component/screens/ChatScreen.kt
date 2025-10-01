package com.example.rmp_front.ui_component.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.example.rmp_front.AppColors
import com.example.rmp_front.data.MessageModel
import com.example.rmp_front.data.RetrofitClient
import com.example.rmp_front.ui_component.components.MessageCard
import kotlinx.coroutines.launch
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(chatId: String, navController: NavHostController) {
    var senderId by remember { mutableStateOf("user_1") }
    var messageText by remember { mutableStateOf("") }
    val messages = remember { mutableStateListOf<Pair<String, String>>() }
    val coroutineScope = rememberCoroutineScope()
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chat: $chatId") },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = AppColors.TopBar),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }){
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = AppColors.TextPrimary)
                    }
                }
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColors.TopBar)
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier
                        .weight(0.8f)
                        .clip(RoundedCornerShape(32.dp))
                        .background(AppColors.InputBackground)
                        .padding(horizontal = 4.dp, vertical = 2.dp),
                    placeholder = { Text("Type here", color = AppColors.PlaceholderText) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = AppColors.InputBackground,
                        unfocusedContainerColor = AppColors.InputBackground,
                        focusedTextColor = AppColors.TextPrimary,
                        unfocusedTextColor = AppColors.TextPrimary
                    ),
                    keyboardOptions = KeyboardOptions.Default,
                    keyboardActions = KeyboardActions(onDone = { /* Обработка Enter */ })
                )
                Spacer(modifier = Modifier.width(8.dp))
                Button(
                    onClick = {
                        if (messageText.isNotEmpty()) {
                            val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
                            coroutineScope.launch {
                                try {
                                    val message = MessageModel(chatId = chatId, senderId = senderId, body = messageText)
                                    val response: Response<MessageModel> = RetrofitClient.apiService.sendMessage(message)
                                    if (response.isSuccessful) {
                                        messages.add(Pair("Отправлено: $messageText", time))
                                        coroutineScope.launch { listState.scrollToItem(messages.size - 1) }
                                    } else {
                                        messages.add(Pair("Ошибка: ${response.code()}", time))
                                    }
                                } catch (e: Exception) {
                                    messages.add(Pair("Ошибка сети: ${e.message}", time))
                                }
                                messageText = ""
                            }
                        }
                    },
                    modifier = Modifier.size(40.dp),
                    shape = CircleShape,
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2A2A2A))
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowUpward,
                        contentDescription = "Send",
                        tint = AppColors.TextPrimary
                    )
                }
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .focusable()
                .background(AppColors.Background),
            state = listState
        ) {
            items(messages) { message ->
                MessageCard(message.first, message.second)
            }
        }
    }
}