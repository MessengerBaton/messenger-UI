package com.example.rmp_front.ui_component.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rmp_front.AppColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.RectangleShape
import androidx.navigation.NavController
import com.example.rmp_front.ui_component.components.SettingsItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FriendProfileScreen(navController: NavController) {
    var profileImage by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("Kitty") }
    var username by remember { mutableStateOf("@super_kitty") }
    var phoneNumber by remember { mutableStateOf("89226593565") }
    var status by remember { mutableStateOf("hi i'm Kitty") }
    var selectedTab by remember { mutableStateOf(0) }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .clip(RectangleShape)
                    .background(AppColors.BaseColor)
            )

            Box(
                modifier = Modifier.padding(start = 16.dp, top = 10.dp)
                    .align(Alignment.TopStart)
                    .clickable {  }
            ) {
                IconButton(onClick = {navController.popBackStack() },
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = AppColors.TextPrimary
                    )
                }
            }
            // Блюр
            GradientBlurOverlay(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .align(Alignment.BottomStart)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp, bottom = 14.dp)
                    .align(Alignment.BottomStart),


            ) {
                Text(
                    text = username,
                    color = AppColors.TextPrimary,
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                Row(modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(end = 40.dp),
                verticalAlignment = Alignment.CenterVertically,
                ){
                    IconButton(
                        onClick = {navController.popBackStack() } ,
                        modifier = Modifier
                            .background(
                                color = AppColors.InputBackground.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(2.dp),

                        ) {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search in chat",
                            tint = AppColors.TextSecondary
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))

                    IconButton(
                        onClick = { navController.popBackStack() } ,
                        modifier = Modifier
                            .background(
                                color = AppColors.InputBackground.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(12.dp)
                            )
                            .padding(2.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.MoreHoriz,
                            contentDescription = "More",
                            tint = AppColors.TextSecondary
                        )
                    }
                }


            }

        }


        Column(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 40.dp)
        ) {
            Text(
                text = "Kitty name",
                color = AppColors.TextSecondary,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 20.dp)
            )
            SettingsItem(text = name, type = "none", subtitle = "")

            Text(
                text = "Kitty number",
                color = AppColors.TextSecondary,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 20.dp)
            )
            SettingsItem(text = phoneNumber, type = "none", subtitle = "")

            Text(
                text = "Kitty info",
                color = AppColors.TextSecondary,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 20.dp)
            )
            SettingsItem(text = status, type = "none", subtitle = "")
        }


        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(AppColors.BaseColor)
                    .height(40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Photo",
                    color = if (selectedTab == 0) AppColors.TextPrimary else AppColors.TextSecondary,
                    modifier = Modifier
                        .clickable { selectedTab = 0 }
                )

                Text(
                    text = "Video",
                    color = if (selectedTab == 1) AppColors.TextPrimary else AppColors.TextSecondary,
                    modifier = Modifier
                        .clickable { selectedTab = 1 }
                )
            }

            // ----- GRID -----
            if (selectedTab == 0) {
                Text("Photo", color = AppColors.TextSecondary)
//            отображение сетки под фото
            } else {
                Text("Video", color = AppColors.TextSecondary)
//            отображение сетки под видео
            }
        }
    }
}
