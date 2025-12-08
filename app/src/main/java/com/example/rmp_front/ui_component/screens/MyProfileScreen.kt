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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.Dp
import androidx.navigation.NavController
import com.example.rmp_front.ui_component.components.SettingsItem
import com.example.rmp_front.ui_component.navigation.Routes
import com.example.rmp_front.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyProfileScreen(navController: NavController, mainViewModel: MainViewModel) {

    val user by mainViewModel.user.collectAsState()

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
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
                    .background(MaterialTheme.colorScheme.primary)
            )
            Box(
                modifier = Modifier.padding(end = 16.dp, top = 16.dp)
                    .align(Alignment.TopEnd)
                .clickable {
                    navController.navigate(Routes.CHANGE_PROFILE)
                }
            ) {
                Text(
                    text = "Change",
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.background.copy(alpha = 0.45f), shape = CircleShape)
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
                    text = user!!.name,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(bottom = 10.dp)

                )
                Text(
                    text = user!!.nick,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 16.sp
                )
            }

        }


        Column(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 40.dp, vertical = 15.dp)
        ) {
            Text(
                text = "Kitty number",
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 30.dp)
            )
            SettingsItem(text = user!!.phone, type = "none", subtitle = "")

            Text(
                text = "Kitty info",
                color =MaterialTheme.colorScheme.onSecondary,
                fontSize = 14.sp,
                modifier = Modifier.padding(start = 30.dp)
            )
            SettingsItem(text = user?.about ?: "", type = "none", subtitle = "")
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
                        MaterialTheme.colorScheme.tertiary.copy(alpha = 0.15f),
                        MaterialTheme.colorScheme.tertiary.copy(alpha = 0.45f)
                    )
                )
            )
    )
}

