package com.example.rmp_front.ui_component.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rmp_front.AppColors
import com.example.rmp_front.R
import com.example.rmp_front.data.ServerClient
import com.example.rmp_front.ui_component.navigation.Routes

@Composable
fun LoginScreen(navController: NavController) {

    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(modifier = Modifier.fillMaxSize()
        .background(AppColors.Background)) {

        Text(
            text = "Welcome to BatonGram!",
            color = AppColors.TextPrimary,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,

            modifier = Modifier.padding(top = 200.dp)
                .align(Alignment.TopCenter)
        )

        Text(
            text = "Please enter your data to log in",
            color = AppColors.TextPrimary,
            fontSize = 16.sp,

            modifier = Modifier.padding(top = 320.dp)
                .align(Alignment.TopCenter)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 50.dp)
                .padding(top = 150.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            OutlinedTextField(
                value = phone,
                onValueChange = { phone = it },
                label = { Text("Phone number") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = AppColors.TextPrimary,
                    unfocusedTextColor = AppColors.TextPrimary,
                    cursorColor = AppColors.TextPrimary,
                ),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()


            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = AppColors.TextPrimary,
                    unfocusedTextColor = AppColors.TextPrimary,
                    cursorColor = AppColors.TextPrimary,
                ),
                visualTransformation = PasswordVisualTransformation(),
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {
                    // логику проверки в бд надо написать
                    if (phone.isNotEmpty() && password.isNotEmpty()) {
                        if (phone.matches(Regex("89\\d{9}\$")) || phone.matches(Regex("\\+79\\d{9}\$"))) {
                            // проверка в бд в теории как то так
//                            val resp = ServerClient.login(phone, password)
//                            if (resp.success) {
                                // вход успешный
//                                navController.navigate(Routes.CHATS_LIST)
//                            }

                            navController.navigate(Routes.CHATS_LIST)
                        }
                    }

                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = AppColors.BaseColor
                )
            ) {
                Text(text = "Log In")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = {
                navController.navigate(Routes.REGISTER)
            }) {
                Text("Create account")
            }
        }
    }
}
