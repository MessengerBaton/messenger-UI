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
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.rmp_front.AppColors
import com.example.rmp_front.viewmodel.ChatsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.draw.clip

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatsListScreen(navController: NavHostController) {
    val viewModel: ChatsViewModel = viewModel()
    val chats by viewModel.chats.collectAsState()
    var searchQuery by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Chats", color = AppColors.TextPrimary, fontSize = 20.sp) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = AppColors.TopBar,
                    titleContentColor = AppColors.TextPrimary
                ),
                actions = {
                    Box {
                        IconButton(onClick = { expanded = true }) {
                            Icon(
                                imageVector = Icons.Filled.Add,
                                contentDescription = "Add chat",
                                tint = AppColors.TextPrimary
                            )
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false },
                            modifier = Modifier.background(AppColors.InputBackground)
                        ) {
                            DropdownMenuItem(
                                text = { Text("Создать группу", color = AppColors.TextPrimary) },
                                onClick = {
                                    expanded = false
                                }
                            )
                            DropdownMenuItem(
                                text = { Text("Создать контакт", color = AppColors.TextPrimary) },
                                onClick = {
                                    expanded = false
                                }
                            )
                        }
                    }
                }
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .background(AppColors.TopBar)
            ) {
                TextField(
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                        .height(55.dp)
                        .clip(RoundedCornerShape(24.dp))
                        .background(AppColors.InputBackground),
                    placeholder = { Text("Search", color = AppColors.PlaceholderText) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = AppColors.InputBackground,
                        unfocusedContainerColor = AppColors.Background,
                        focusedTextColor = AppColors.TextPrimary,
                        unfocusedTextColor = AppColors.TextPrimary,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    singleLine = true,
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Filled.Search,
                            contentDescription = "Search icon",
                            tint = AppColors.PlaceholderText
                        )
                    }
                )

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
                                .padding(horizontal = 16.dp, vertical = 15.dp)
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
                                    .weight(1f),
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Medium
                            )
                            Text(
                                text = "12:20",
                                color = AppColors.TextSecondary,
                                modifier = Modifier.align(Alignment.CenterVertically)
                            )
                        }
                        Divider(
                            color = AppColors.TopBar.copy(alpha = 0.2f),
                            thickness = 0.3.dp,
                            modifier = Modifier.padding(horizontal = 16.dp)
                        )
                    }
                }
            }
        }
    )
}