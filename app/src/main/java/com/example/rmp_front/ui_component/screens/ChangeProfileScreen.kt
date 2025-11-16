package com.example.rmp_front.ui_component.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.rmp_front.AppColors
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.navigation.NavController
import com.example.rmp_front.ui_component.components.ChangeItem

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChangeProfileScreen(navController: NavController) {
    var profileImage by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("Kitty") }
    var username by remember { mutableStateOf("@super_kitty") }
    var phoneNumber by remember { mutableStateOf("89222659356") }
    var status by remember { mutableStateOf("hi i'm Kitty") }
    var birthday by remember { mutableStateOf("") }
    var expanded by remember { mutableStateOf(false) }

    Column(
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
            Row {
                Box(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .size(240.dp)
                        .clip(CircleShape)
                        .background(AppColors.BaseColor)
                )
                Box(
                    modifier = Modifier
                        .clickable { expanded = true }
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(AppColors.BaseColor)
                        .align ( Alignment.Bottom ),

                ){
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.background(AppColors.InputBackground)
                    ) {
                        DropdownMenuItem(
                            text = { Text("Change photo", color = AppColors.TextPrimary) },
                            onClick = {
                                expanded = false
                            }
                        )
                        DropdownMenuItem(
                            text = { Text("Delete photo", color = AppColors.TextPrimary) },
                            onClick = {
                                expanded = false
                            }
                        )
                    }
                }
            }
            Box(
                modifier = Modifier.padding(end = 16.dp, top = 10.dp)
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

            Box(
                modifier = Modifier.padding(end = 16.dp, top = 16.dp)
                    .align(Alignment.TopEnd)
                    .clickable {navController.popBackStack()}

            ) {
                Text(
                    text = "Save",
                    color = AppColors.TextPrimary,
                    modifier = Modifier
                        .background(AppColors.BaseColor, shape = CircleShape)
                        .padding(10.dp)
                )
            }
        }

        ChangeItem(value = name,
            onValueChange = { name = it },
            text = "Kitty name",)

        ChangeItem(value = username,
            onValueChange = { username = it },
            text = "Kitty nickname",)

        ChangeItem(value = phoneNumber,
            onValueChange = { phoneNumber = it },
            text = "Kitty number")

        ChangeItem(value = status,
            onValueChange = { status = it },
            text = "Kitty info")

        ChangeItem(value = birthday,
            onValueChange = { birthday = it },
            text = "Kitty birthday")

    }
}