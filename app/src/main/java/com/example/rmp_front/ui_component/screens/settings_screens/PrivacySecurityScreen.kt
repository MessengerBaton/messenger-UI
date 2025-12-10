package com.example.rmp_front.ui_component.screens.settings_screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
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
fun PrivacySecurityScreen(
    navController: NavController
) {
    var blockUnknownCalls by remember { mutableStateOf(true) }
    var showOnlineStatus by remember { mutableStateOf(true) }
    var showReadReceipts by remember { mutableStateOf(true) }

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
                onClick = { navController.navigateUp() },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }

            Text(
                text = "Privacy & Security",
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
                    text = "Privacy",
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 35.dp, bottom = 10.dp)
                )
            }
            item {
                SettingsItem(
                    text = "Blocked Users",
                    type = "sub",
                    subtitle = "0 blocked"
                )
            }
            item {
                Switcher(
                    text = "Show online status",
                    isChecked = showOnlineStatus,
                    onCheckedChange = { showOnlineStatus = it }
                )
            }
            item {
                Switcher(
                    text = "Show read receipts",
                    isChecked = showReadReceipts,
                    onCheckedChange = { showReadReceipts = it }
                )
            }

            item {
                Text(
                    text = "Security",
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 35.dp, top = 20.dp, bottom = 10.dp)
                )
            }
            item {
                Switcher(
                    text = "Block unknown calls",
                    isChecked = blockUnknownCalls,
                    onCheckedChange = { blockUnknownCalls = it }
                )
            }
            item {
                SettingsItem(
                    text = "Two-Step Verification",
                    type = "sub",
                    subtitle = "Disabled"
                )
            }
        }
    }
}