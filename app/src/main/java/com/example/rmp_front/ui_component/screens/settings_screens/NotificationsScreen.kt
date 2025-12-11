package com.example.rmp_front.ui_component.screens.settings_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rmp_front.ui_component.components.SettingsItem
import com.example.rmp_front.ui_component.components.Switcher

@Composable
fun NotificationsScreen(
    navController: NavController
) {
    var notificationsEnabled by remember { mutableStateOf(true) }
    var soundsEnabled by remember { mutableStateOf(true) }
    var vibrationsEnabled by remember { mutableStateOf(true) }
    var newMessagesEnabled by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(all = 16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {
                    navController.navigateUp()
                },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }

            Text(
                text = "Notifications & Sounds",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            )
        }

        LazyColumn {
            item {
                Text(
                    text = "Notifications",
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 35.dp, bottom = 10.dp)
                )
            }
            item {
                Switcher(
                    text = "Enable notifications",
                    isChecked = notificationsEnabled,
                    onCheckedChange = { notificationsEnabled = it }
                )
            }
            item {
                Switcher(
                    text = "Sounds",
                    isChecked = soundsEnabled,
                    onCheckedChange = { soundsEnabled = it }
                )
            }
            item {
                Switcher(
                    text = "Vibrations",
                    isChecked = vibrationsEnabled,
                    onCheckedChange = { vibrationsEnabled = it }
                )
            }

            item {
                Text(
                    text = "Message Notifications",
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 35.dp, top = 20.dp, bottom = 10.dp)
                )
            }
            item {
                Switcher(
                    text = "New messages",
                    isChecked = newMessagesEnabled,
                    onCheckedChange = { newMessagesEnabled = it }
                )
            }
            item {
                SettingsItem(
                    text = "Message sound",
                    type = "sub",
                    subtitle = "Default"
                )
            }
            item {
                SettingsItem(
                    text = "Group notifications",
                    type = "sub",
                    subtitle = "Mute"
                )
            }
        }
    }
}