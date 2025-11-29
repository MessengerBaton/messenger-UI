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
import com.example.rmp_front.viewmodel.ChatsViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.rmp_front.ui_component.components.AddButton
import com.example.rmp_front.ui_component.components.CustomTextField
import com.example.rmp_front.ui_component.components.MiniChatInList

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
                title = { Text("Chats",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 30.sp,
                    modifier = Modifier.padding(start = 10.dp)

                ) },
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
                            text = { Text("Создать группу", color = MaterialTheme.colorScheme.onPrimary) },
                            onClick = {
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Создать контакт", color = MaterialTheme.colorScheme.onPrimary) },
                            onClick = {
                                expanded = false
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

                // кнопочка чтоб попасть на /tmp
//                Button(
//                    onClick = { navController.navigate(Routes.TMP) },
//                    modifier = Modifier.align(Alignment.CenterHorizontally)
//                ) {
//                    Text("СЕРВЕР")
//                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(MaterialTheme.colorScheme.background)
                ) {
                    val filteredChats = chats.filter { it.name.contains(searchQuery, ignoreCase = true) }

                    items(filteredChats) { chat ->
                        MiniChatInList(
                            navController = navController,
                            chat = chat
                        )
                    }
                }
            }
        }
    )
}

