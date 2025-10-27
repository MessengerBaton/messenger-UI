package com.example.rmp_front.ui_component.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rmp_front.AppColors
import androidx.compose.material3.ExperimentalMaterial3Api

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    var profileImage by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("Kitty") }
    var username by remember { mutableStateOf("@super_kitty") }
    var phoneNumber by remember { mutableStateOf("89226593565") }
    var status by remember { mutableStateOf("hi i'm Kitty") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(AppColors.Background)
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(150.dp)
                    .clip(CircleShape)
                    .background(AppColors.InputBackground)
            )
            Text(
                text = "Change",
                color = AppColors.TextPrimary,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .clickable { }
                    .background(AppColors.InputBackground, shape = CircleShape)
                    .padding(8.dp)
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = name,
                color = AppColors.TextPrimary,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
            Text(
                text = username,
                color = AppColors.TextSecondary,
                fontSize = 16.sp
            )
        }

        OutlinedTextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            label = { Text("Kitty number", color = AppColors.PlaceholderText) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = AppColors.InputBackground,
                focusedIndicatorColor = AppColors.TextSecondary,
            ),
            textStyle = TextStyle(color = AppColors.InputText),
            shape = RoundedCornerShape(12.dp)
        )

        OutlinedTextField(
            value = status,
            onValueChange = { status = it },
            label = { Text("Kitty something", color = AppColors.PlaceholderText) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            colors = TextFieldDefaults.textFieldColors(
                containerColor = AppColors.InputBackground,
                focusedIndicatorColor = AppColors.TextSecondary,
            ),
            textStyle = TextStyle(color = AppColors.InputText),
            shape = RoundedCornerShape(12.dp)
        )
    }
}