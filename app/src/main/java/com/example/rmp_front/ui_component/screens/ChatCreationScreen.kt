package com.example.rmp_front.ui_component.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.rmp_front.data.SessionManager
import com.example.rmp_front.data.models.User
import com.example.rmp_front.ui_component.components.CustomTextField
import com.example.rmp_front.ui_component.components.SelectedUserItem
import com.example.rmp_front.ui_component.components.UserSearchItem
import com.example.rmp_front.viewmodel.chatCreation.ChatCreationViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatCreationScreen(navController: NavHostController) {
    val context = LocalContext.current

    val viewModel: ChatCreationViewModel = viewModel()
    val users by viewModel.users.collectAsState()
    val focusManager = LocalFocusManager.current
    val coroutineScope = rememberCoroutineScope()


    var searchQuery by remember { mutableStateOf("") }
    var searchResults by remember { mutableStateOf(emptyList<User>()) }
    var selectedUsers by remember { mutableStateOf<List<User>>(emptyList()) }
    var chatTitle by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.getUsers()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "New Chat",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 24.sp
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onPrimary
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {

            // 🔹 Название чата (если группа)
            if (selectedUsers.size > 1) {
                CustomTextField(
                    icon = false,
                    placeHolder = "Chat title",
                    value = chatTitle,
                    onValueChange = { chatTitle = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }

            // 🔹 Выбранные пользователи
            if (selectedUsers.isNotEmpty()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp, vertical = 8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(
                            text = "Chat members:",
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )

                        LazyColumn(
                            modifier = Modifier.heightIn(max = 200.dp)
                        ) {
                            items(selectedUsers) { user ->
                                SelectedUserItem(
                                    user = user,
                                    onRemoveClick = {
                                        selectedUsers = selectedUsers - user
                                    }
                                )
                            }
                        }
                    }
                }
            }

            // 🔹 Поиск пользователей
            CustomTextField(
                icon = true,
                placeHolder = "Search users by name or username",
                value = searchQuery,
                onValueChange = { query ->
                    searchQuery = query

                    // 🔸 Локальная логика поиска (заглушка)
                    searchResults =
                        if (query.length >= 2) {
                            filterUsers(query, users)
                        } else {
                            emptyList()
                        }
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                trailingIcon = {
                    if (searchQuery.isNotEmpty()) {
                        IconButton(onClick = {
                            searchQuery = ""
                            searchResults = emptyList()
                        }) {
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = "Clear search",
                                tint = MaterialTheme.colorScheme.onPrimary
                            )
                        }
                    }
                }
            )

            // 🔹 Результаты поиска
            when {
                searchResults.isNotEmpty() -> {
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f)
                    ) {
                        items(searchResults) { user ->
                            if (!selectedUsers.any { it.id == user.id }) {
                                UserSearchItem(
                                    user = user,
                                    onClick = {
                                        selectedUsers = selectedUsers + user
                                    }
                                )
                            }
                        }
                    }
                }

                searchQuery.length >= 2 -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "No users found",
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                        )
                    }
                }else -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .weight(1f),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = "Type at least 2 characters to search",
                            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.6f)
                        )
                    }
                }
            }

            // 🔹 Кнопка создания чата
            if (selectedUsers.isNotEmpty()) {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            val userId = SessionManager.getUserId(context) ?: return@launch

                            focusManager.clearFocus()

                            if (selectedUsers.size > 1) {
                                viewModel.createGroup(userId, chatTitle, selectedUsers)
                            } else{
                                viewModel.createChat(userId, selectedUsers.first())
                            }

                            navController.previousBackStackEntry
                                ?.savedStateHandle
                                ?.set("chat_created", true)

                            navController.popBackStack()
                        }

                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    )
                ) {
                    Text(
                        text = if (selectedUsers.size == 1)
                            "Start Chat with ${selectedUsers.first().name}"
                        else
                            "Create Group Chat ${chatTitle}",
                        fontSize = 16.sp
                    )
                }
            }
        }
    }
}

private fun filterUsers(
    query: String,
    users: List<User>
): List<User> {
    if (query.length < 2) return emptyList()

    val q = query.trim().lowercase()

    return users.filter { user ->
        user.name.lowercase().contains(q) ||
                user.nick.lowercase().contains(q)
    }
}


