package com.example.rmp_front.ui_component.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.platform.LocalContext
import com.example.rmp_front.data.SessionManager
import com.example.rmp_front.ui_component.components.AddButton
import com.example.rmp_front.ui_component.components.CustomTextField
import com.example.rmp_front.ui_component.components.MiniChatInList
import com.example.rmp_front.ui_component.components.MiniGroupInList
import com.example.rmp_front.ui_component.navigation.Routes
import com.example.rmp_front.viewmodel.chatsList.ChatsListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatsListScreen(navController: NavHostController) {

    val context = LocalContext.current

    val viewModel: ChatsListViewModel = viewModel()
    val chats by viewModel.chats.collectAsState()
    val groups by viewModel.groups.collectAsState()

    var searchQuery by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    LaunchedEffect(Unit){
        val userId = SessionManager.getUserId(context)
        if (userId != null) {
            viewModel.loadAll(userId)
        }
    }
    val chatCreated by navController.currentBackStackEntry!!
            .savedStateHandle
            .getStateFlow<Boolean>("chat_created", false)
            .collectAsState()

    LaunchedEffect(chatCreated) {
        if (chatCreated) {
            val userId = SessionManager.getUserId(context)
            if (userId != null) {
                viewModel.loadAll(userId)
            }

            navController.currentBackStackEntry!!
                .savedStateHandle["chat_created"] = false
        }
    }


    Scaffold(

        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Chats",
                        color = MaterialTheme.colorScheme.onPrimary,
                        fontSize = 30.sp,
                        modifier = Modifier.padding(start = 10.dp)
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.primary
                ),
                actions = {
                    AddButton(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        onClick = { expanded = true }
                    )

                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.background(MaterialTheme.colorScheme.background)
                    ) {
                        DropdownMenuItem(
                            text = {
                                Text("Create group", color = MaterialTheme.colorScheme.onPrimary)
                            },
                            onClick = {
                                expanded = false
                                navController.navigate(Routes.CREATE_CHAT)
                            }
                        )
                        DropdownMenuItem(
                            text = {
                                Text("Create contact", color = MaterialTheme.colorScheme.onPrimary)
                            },
                            onClick = {
                                expanded = false
                                navController.navigate(Routes.CREATE_CHAT)
                            }
                        )
                    }
                }
            )
        },


        content = { padding ->
            Column(
                modifier = Modifier
                    .padding(padding)
                    .background(MaterialTheme.colorScheme.primary)
            ) {

                CustomTextField(
                    icon = true,
                    placeHolder = "Search",
                    value = searchQuery,
                    onValueChange = { searchQuery = it },
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.primary)
                        .fillMaxWidth()
                        .padding(horizontal = 15.dp, vertical = 6.dp)
                )


                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    val filteredChats = chats.filter {
                        it.title.contains(searchQuery, ignoreCase = true)
                    }

                    items(filteredChats) { chat ->
                        MiniChatInList(
                            navController = navController,
                            chat = chat
                        )
                    }

                    val filteredGroups = groups.filter {
                        it.name.contains(searchQuery, ignoreCase = true)
                    }

                    items(filteredGroups) { group ->
                        MiniGroupInList(
                            navController = navController,
                            group = group
                        )
                    }
                }
            }
        }
    )
}
