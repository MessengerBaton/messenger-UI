package com.example.rmp_front.ui_component.screens



import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Send
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.rmp_front.data.models.Message
import com.example.rmp_front.ui_component.components.AddButton
import com.example.rmp_front.ui_component.components.CustomTextField
import com.example.rmp_front.ui_component.components.MessageCard
import com.example.rmp_front.viewmodel.chat.ChatViewModel
import com.example.rmp_front.viewmodel.group.GroupViewModel
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupScreen(groupId: String, navController: NavHostController) {

    var messageText by remember { mutableStateOf("") }
    val listState = rememberLazyListState()
    var expanded by remember { mutableStateOf(false) }

    val chatViewModel: ChatViewModel = viewModel()
    val groupViewModel: GroupViewModel = viewModel()

    val groupInfo by groupViewModel.group.collectAsState()

    val messages by chatViewModel.message.collectAsState()

    LaunchedEffect(groupId){
        groupViewModel.loadGroup(groupId)
    }


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ){

                        IconButton(onClick = { navController.popBackStack() },
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                contentDescription = "Back",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }

                        Text(
                            text = groupInfo?.name ?: "",
                            color = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier
                                .weight(1f)
                                .clickable {navController.navigate("group_info/${groupInfo?.id}")},
                            textAlign = TextAlign.Center,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )

                        Box(
                            modifier = Modifier
                                .padding(end = 30.dp)
                                .size(44.dp)
                                .background(Color.Gray.copy(alpha = 0.5f), shape = CircleShape)
                                .clickable {navController.navigate("friend_profile/${groupInfo?.id}")},
                        )

                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
            )
        },
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .height(60.dp)
            ) {
                AddButton(
                    modifier = Modifier
                        .padding(start = 5.dp, top = 2.dp),
                    onClick = { expanded = true }

                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.background(MaterialTheme.colorScheme.background)
                ) {
                    DropdownMenuItem(
                        text = { Text("Добавить фото", color = MaterialTheme.colorScheme.onPrimary) },
                        onClick = {
                            expanded = false
                        }
                    )
                    DropdownMenuItem(
                        text = { Text("Добавить видео", color = MaterialTheme.colorScheme.onPrimary) },
                        onClick = {
                            expanded = false
                        }
                    )
                }

                CustomTextField(
                    icon = false,
                    placeHolder = "Type here",
                    value = messageText,
                    onValueChange = { messageText = it },
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .width(290.dp)
                        .padding(vertical = 6.dp)
                )

                Button(
                    onClick = {
                        if (messageText.isNotEmpty()) {
                            val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())

                            val message = Message(
                                id = UUID.randomUUID().toString(),
                                chatId = groupId,
                                timestamp = time,
                                text = messageText,
                                isFromMe = true
                            )

//                            coroutineScope.launch {
//                                try {
//                                    val response: Response<Message> = RetrofitClient.apiService.sendMessage(message)
//
//                                    if (response.isSuccessful) {
//                                        messages.add(Pair("Отправлено: $messageText", time))
//                                        coroutineScope.launch { listState.scrollToItem(messages.size - 1) }
//                                    } else {
//                                        messages.add(Pair("Ошибка: ${response.code()}", time))
//                                    }
//                                } catch (e: Exception) {
//                                    messages.add(Pair("Ошибка сети: ${e.message}", time))
//                                }
                            chatViewModel.sendMessage(message)
                            messageText = ""
//                            }
                        }
                    },
                    modifier = Modifier.padding(0.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary)
                ) {
                    Icon(
                        imageVector = Icons.Default.Send,
                        contentDescription = "Send",
                        tint = MaterialTheme.colorScheme.onPrimary,
                        modifier = Modifier.size(20.dp)

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
                .background(MaterialTheme.colorScheme.background),
            state = listState,
            reverseLayout = false,
            contentPadding = PaddingValues(bottom = 8.dp),
        ) {
            items(messages) { message ->
                MessageCard(message.text, message.timestamp, message.isFromMe)
            }
        }
    }
}