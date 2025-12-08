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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.rmp_front.data.models.Chat

@Composable

fun MiniChatInList(
    navController: NavHostController,
    chat: Chat
){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { navController.navigate("chat/${chat.id}") }
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
                text = chat.title,
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.padding(start = 10.dp, top = 5.dp)
            )

            Text(
                text = chat?.lastMessage ?: "",
                fontSize = 14.sp,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier.padding(start = 10.dp, top = 15.dp)
            )

        }
        Text(
            text = chat?.timestamp ?: "",
            fontSize = 14.sp,
            color = MaterialTheme.colorScheme.onSecondary,
            modifier = Modifier.padding(start = 180.dp, top = 10.dp)
        )

    }
    Divider(
        color = MaterialTheme.colorScheme.onSecondary,
        thickness = 0.5.dp,
        modifier = Modifier.padding(horizontal = 16.dp)
    )
}