package com.example.rmp_front.ui_component.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChatBubble
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.rmp_front.AppColors
import com.example.rmp_front.ui_component.navigation.Routes

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(
        BottomNavItem("Настройки", Routes.SETTINGS, Icons.Default.Settings),
        BottomNavItem("Чаты", Routes.CHATS_LIST, Icons.Default.ChatBubble),
        BottomNavItem("Профиль", Routes.PROFILE, Icons.Default.Person)
    )

    val routesWithBottomNav = listOf(Routes.SETTINGS, Routes.CHATS_LIST, Routes.PROFILE)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    if (currentRoute in routesWithBottomNav) {
        BottomNavigation(
            backgroundColor = AppColors.TopBar,
            contentColor = AppColors.TextPrimary
        ) {
            items.forEach { item ->
                BottomNavigationItem(
                    icon = { Icon(item.icon, contentDescription = item.label) },
                    label = { Text(item.label) },
                    selected = currentRoute == item.route,
                    selectedContentColor = AppColors.TextPrimary,
                    unselectedContentColor = Color.Gray,
                    onClick = {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}

data class BottomNavItem(val label: String, val route: String, val icon: androidx.compose.ui.graphics.vector.ImageVector)