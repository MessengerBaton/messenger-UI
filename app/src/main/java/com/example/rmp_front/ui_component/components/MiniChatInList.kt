package com.example.rmp_front.ui_component.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.rmp_front.AppColors
import com.example.rmp_front.viewmodel.ChatItem

@Composable

fun MiniChatInList(
    navController: NavHostController,
    chat: ChatItem
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("chat/${chat.name}") }
            .padding(horizontal = 16.dp, vertical = 15.dp)
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 5.dp)
                .size(50.dp)
                .background(Color.Gray.copy(alpha = 0.5f), shape = CircleShape)

        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Text(
                text = "${chat.name}",
                fontSize = 20.sp,
                color = AppColors.TextPrimary,
                modifier = Modifier.padding(start = 10.dp, top = 5.dp)
            )

            Text(
                text = "last message",
                fontSize = 14.sp,
                color = AppColors.TextSecondary,
                modifier = Modifier.padding(start = 10.dp, top = 15.dp)
            )

        }
        Text(
            text = "14:88",
            fontSize = 14.sp,
            color = AppColors.TextSecondary,
            modifier = Modifier.padding(start = 180.dp, top = 10.dp)
        )

    }
    Divider(
        color = AppColors.TextSecondary,
        thickness = 0.5.dp,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}