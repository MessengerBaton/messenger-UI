package com.example.rmp_front.ui_component.screens.settings_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rmp_front.ui_component.components.SettingsItem
import com.example.rmp_front.ui_component.components.Switcher
import com.example.rmp_front.ui_component.navigation.Routes

@Composable
fun SettingsScreen(
    navController: NavController,
    darkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
    ) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(all = 16.dp)
    ) {
        Text(
            text = "Settings",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 30.sp,
            modifier = Modifier.padding(start = 10.dp, bottom = 24.dp)
        )

        LazyColumn {
            item {
                SettingsItem("Notifications and sounds", type = "default", subtitle = "", onClick = {navController.navigate(
                    Routes.NOTIFICATIONS)})
            }
            item {
                SettingsItem("Privacy & Security", type = "default", subtitle = "", onClick = {
                    navController.navigate(Routes.PRIVACY_SECURITY)
                })
            }



            item {
                Switcher("Dark theme", isChecked = darkTheme, onCheckedChange = onThemeChange)
            }
            item {
                SettingsItem("Language", type = "sub", subtitle = "english")
            }


            item {
                Text(
                    text = "About us",
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 150.dp, top = 24.dp, bottom = 10.dp)
                )
            }
            item {
                SettingsItem("Help", type = "default", subtitle = "")
            }
            item {
                SettingsItem("FAQ", type = "default", subtitle = "")
            }
            item {
                SettingsItem("User agreement", type = "default", subtitle = "", onClick = {navController.navigate(
                    Routes.AGREEMENT)})
            }
        }
    }
}
