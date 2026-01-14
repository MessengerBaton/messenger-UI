package com.example.rmp_front.ui_component.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.rmp_front.ui_component.screens.ChangeProfileScreen
import com.example.rmp_front.ui_component.screens.ChatScreen
import com.example.rmp_front.ui_component.screens.ChatsListScreen
import com.example.rmp_front.ui_component.screens.FriendProfileScreen
import com.example.rmp_front.ui_component.screens.GroupScreen
import com.example.rmp_front.ui_component.screens.LoginScreen
import com.example.rmp_front.ui_component.screens.MyProfileScreen
import com.example.rmp_front.ui_component.screens.RegisterScreen
import com.example.rmp_front.viewmodel.MainViewModel
import com.example.rmp_front.ui_component.screens.settings_screens.*

//import com.example.rmp_front.ui_component.screens.TmpScreen

object Routes {

    const val SETTINGS = "settings"
    const val NOTIFICATIONS = "notifications"
    const val PRIVACY_SECURITY = "privacy_security"
    const val PROFILE = "profile"
    const val CHATS_LIST = "chats_list"
    const val CHAT = "chat/{chatId}"
    const val CHANGE_PROFILE = "change_profile"
    const val FRIEND_PROFILE = "friend_profile/{userId}"
    const val LOGIN = "login"
    const val REGISTER = "register"
    const val GROUP = "group"
    const val AGREEMENT = "agreement"

//    const val TMP = "tmp" // тест подключения к серверу
}

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    darkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit,
    mainViewModel: MainViewModel

    ) {
    NavHost(navController = navController, startDestination = Routes.LOGIN) {
        composable(Routes.LOGIN){
            LoginScreen(navController = navController)
        }
        composable(
            route = Routes.CHATS_LIST,
            arguments = listOf(
                navArgument("userId") { defaultValue = "" },
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")!!
            ChatsListScreen(userId = userId, navController = navController)
        }

        composable(
            route = Routes.CHAT,
            arguments = listOf(
                navArgument("chatId") { defaultValue = "" }
            )
        ) { backStackEntry ->
            val chatId = backStackEntry.arguments?.getString("chatId")!!
            ChatScreen(chatId = chatId, navController = navController)
        }

//        composable(Routes.TMP) {
//            TmpScreen(navController = navController)
//        }
        composable(Routes.PROFILE) {
            MyProfileScreen(navController = navController, mainViewModel = mainViewModel)
        }
        composable(Routes.SETTINGS) {
            SettingsScreen(
                navController = navController,
                darkTheme = darkTheme,
                onThemeChange = onThemeChange
                )
        }
        composable(Routes.NOTIFICATIONS) {
            NotificationsScreen(navController = navController)
        }
        composable(Routes.PRIVACY_SECURITY) {
            PrivacySecurityScreen(navController = navController)
        }
        composable(Routes.CHANGE_PROFILE){
            ChangeProfileScreen(navController = navController,  mainViewModel = mainViewModel)
        }
        composable(
            route = Routes.FRIEND_PROFILE,
            arguments = listOf(
                navArgument("userId") { defaultValue = "" }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")!!
            FriendProfileScreen(userId = userId, navController = navController)
        }

        composable(Routes.REGISTER){
            RegisterScreen(navController = navController)
        }

        composable(
            route = Routes.GROUP,
            arguments = listOf(
                navArgument("groupId") { defaultValue = "" }
            )
        ) { backStackEntry ->
            val groupId = backStackEntry.arguments?.getString("groupId")!!
            GroupScreen(groupId = groupId, navController = navController)
        }

        composable(Routes.AGREEMENT){
            UserAgreementScreen(navController = navController)
        }
    }
}
