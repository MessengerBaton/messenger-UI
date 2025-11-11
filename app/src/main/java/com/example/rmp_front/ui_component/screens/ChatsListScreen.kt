package com.example.rmp_front.ui_component.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.rmp_front.AppColors
import com.example.rmp_front.viewmodel.ChatsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.draw.clip
import com.example.rmp_front.ui_component.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatsListScreen(navController: NavHostController) {
    val viewModel: ChatsViewModel = viewModel()
    val chats by viewModel.chats.collectAsState()
    var searchQuery by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chats List", color = AppColors.TextPrimary) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppColors.TopBar,
                    titleContentColor = AppColors.TextPrimary
                )
            )
        },
        content = { padding ->
            Column(modifier = Modifier
                .padding(padding)
                .background(AppColors.Background)
            ) {
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp)
                        .height(60.dp)
                        .clip(RoundedCornerShape(32.dp))
                        .background(AppColors.InputBackground),
                    placeholder = { Text("Search chats", color = AppColors.PlaceholderText) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = AppColors.InputBackground,
                        unfocusedContainerColor = AppColors.InputBackground,
                        focusedTextColor = AppColors.TextPrimary,
                        unfocusedTextColor = AppColors.TextPrimary
                    ),
                    singleLine = true
                )

                // кнопочка чтоб попасть на /tmp
                Button(
                    onClick = { navController.navigate(Routes.TMP) },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    Text("СЕРВЕР")
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(AppColors.Background)
                ) {
                    val filteredChats = chats.filter { it.name.contains(searchQuery, ignoreCase = true) }
                    items(filteredChats) { chat ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { navController.navigate("chat/${chat.id}") }
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(40.dp)
                                    .background(Color.Gray.copy(alpha = 0.5f), shape = CircleShape)
                            )
                            Spacer(modifier = Modifier.width(12.dp))
                            Text(
                                text = chat.name,
                                color = AppColors.TextPrimary,
                                modifier = Modifier
                                    .align(Alignment.CenterVertically)
                                    .weight(1f)
                            )
                        }
                        Divider(
                            color = AppColors.TextSecondary,
                            thickness = 0.5.dp,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    )
}