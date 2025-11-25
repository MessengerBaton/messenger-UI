package com.example.rmp_front.ui_component.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.rmp_front.ui_component.screens.ChangeProfileScreen
import com.example.rmp_front.ui_component.screens.ChatScreen
import com.example.rmp_front.ui_component.screens.ChatsListScreen
import com.example.rmp_front.ui_component.screens.FriendProfileScreen
import com.example.rmp_front.ui_component.screens.LoginScreen
import com.example.rmp_front.ui_component.screens.MyProfileScreen
import com.example.rmp_front.ui_component.screens.RegisterScreen
import com.example.rmp_front.ui_component.screens.SettingsScreen
import com.example.rmp_front.ui_component.screens.TmpScreen

object Routes {

    const val SETTINGS = "settings"
    const val PROFILE = "profile"
    const val CHATS_LIST = "chats_list"
    const val CHAT = "chat/{chatId}"
    const val CHANGE_PROFILE = "change_profile"
    const val FRIEND_PROFILE = "friend_profile"
    const val LOGIN = "login"
    const val REGISTER = "register"

    const val TMP = "tmp" // тест подключения к серверу
}

@Composable
fun NavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN){
            LoginScreen(navController = navController)
        }
        composable(Routes.CHATS_LIST) {
            ChatsListScreen(navController = navController)
        }
        composable(Routes.CHAT) { backStackEntry ->
            val chatId = backStackEntry.arguments?.getString("chatId") ?: "chat_1"
            ChatScreen(chatId = chatId, navController = navController)
        }
        composable(Routes.TMP) {
            TmpScreen(navController = navController)
        }
        composable(Routes.PROFILE) {
            MyProfileScreen(navController = navController)
        }
        composable(Routes.SETTINGS) {
            SettingsScreen(navController = navController)
        }
        composable(Routes.CHANGE_PROFILE){
            ChangeProfileScreen(navController = navController)
        }
        composable(Routes.FRIEND_PROFILE){
            FriendProfileScreen(navController = navController)
        }
        composable(Routes.REGISTER){
            RegisterScreen(navController = navController)
        }
    }
}
