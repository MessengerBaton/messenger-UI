package com.example.rmp_front.ui_component.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rmp_front.AppColors
import com.example.rmp_front.ui_component.components.SettingsItem

@Composable
fun SettingsScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(all = 16.dp)
    ) {
        Text(
            text = "Settings",
            color = AppColors.TextPrimary,
            fontSize = 30.sp,
            modifier = Modifier.padding(start = 10.dp, bottom = 24.dp)
        )

        LazyColumn {
            item {
                Text(
                    text = "Design",
                    color = AppColors.TextSecondary,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 35.dp, bottom = 10.dp)
                )
            }
            item {
                SettingsItem("Бла бла", type = "default", subtitle = "")
            }
            item {
                SettingsItem("Бла бла", type = "default", subtitle = "")
            }


            item {
                Text(
                    text = "Design",
                    color = AppColors.TextSecondary,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 35.dp, top = 10.dp, bottom = 10.dp)
                )
            }
            item {
                SettingsItem("Light theme", type = "switch", subtitle = "")
            }
            item {
                SettingsItem("Language", type = "sub", subtitle = "english")
            }


            item {
                Text(
                    text = "About us",
                    color = AppColors.TextSecondary,
                    fontSize = 16.sp,
                    modifier = Modifier.padding(start = 35.dp, top = 10.dp, bottom = 10.dp)
                )
            }
            item {
                SettingsItem("Help", type = "default", subtitle = "")
            }
            item {
                SettingsItem("FAQ", type = "default", subtitle = "")
            }
            item {
                SettingsItem("User agreement", type = "default", subtitle = "")
            }
        }
    }
}
