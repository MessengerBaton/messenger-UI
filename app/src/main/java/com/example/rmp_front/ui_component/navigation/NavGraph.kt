package com.example.rmp_front.ui_component.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rmp_front.ui_component.screens.ChatScreen
import com.example.rmp_front.ui_component.screens.ChatsListScreen

object Routes {
    const val CHATS_LIST = "chats_list"
    const val CHAT = "chat/{chatId}"
}

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.CHATS_LIST) {
        composable(Routes.CHATS_LIST) {
            ChatsListScreen(navController = navController)
        }
        composable(Routes.CHAT) { backStackEntry ->
            val chatId = backStackEntry.arguments?.getString("chatId") ?: "chat_1"
            ChatScreen(chatId = chatId, navController = navController)
        }
    }
}