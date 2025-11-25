package com.example.rmp_front.ui_component.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rmp_front.AppColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import com.example.rmp_front.ui_component.components.SettingsItem
import com.example.rmp_front.ui_component.navigation.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyProfileScreen(navController: NavController) {
    var profileImage by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("Kitty") }
    var username by remember { mutableStateOf("@super_kitty") }
    var phoneNumber by remember { mutableStateOf("89226593565") }
    var status by remember { mutableStateOf("hi i'm Kitty") }

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
                modifier = Modifier.padding(end = 16.dp, top = 16.dp)
                    .align(Alignment.TopEnd)
                .clickable {
                    navController.navigate(Routes.LOGIN)
                }
            ) {
                Text(
                    text = "Change",
                    color = AppColors.TextPrimary,
                    modifier = Modifier
                        .background(AppColors.InputBackground.copy(alpha = 0.45f), shape = CircleShape)
                        .padding(10.dp)
                )
            }
            // Блюр
            GradientBlurOverlay(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .align(Alignment.BottomStart)
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 50.dp, bottom = 14.dp)
                    .align(Alignment.BottomStart)


            ) {
                Text(
                    text = name,
                    color = AppColors.TextPrimary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 10.dp)

                )
                Text(
                    text = username,
                    color = AppColors.TextSecondary,
                    fontSize = 16.sp
                )
            }

        }


        Column(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 40.dp, vertical = 15.dp)
        ) {
            Text(
                text = "Kitty number",
                color = AppColors.TextSecondary,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 30.dp)
            )
            SettingsItem(text = phoneNumber, type = "none", subtitle = "")

            Text(
                text = "Kitty info",
                color = AppColors.TextSecondary,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 30.dp)
            )
            SettingsItem(text = status, type = "none", subtitle = "")
        }
    }
}





@Composable
fun GradientBlurOverlay(
    modifier: Modifier = Modifier,
    blurRadius: Dp = 30.dp
) {
    Box(
        modifier = modifier
            .blur(blurRadius)
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color.Transparent,
                        Color.Black.copy(alpha = 0.15f),
                        Color.Black.copy(alpha = 0.45f)
                    )
                )
            )
    )
}

