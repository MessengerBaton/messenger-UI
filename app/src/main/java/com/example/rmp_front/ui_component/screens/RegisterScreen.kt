package com.example.rmp_front.ui_component.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.rmp_front.ui_component.components.AppToast
import com.example.rmp_front.ui_component.components.rememberToastState
import com.example.rmp_front.ui_component.navigation.Routes
import com.example.rmp_front.viewmodel.Register.RegisterViewModel
import kotlinx.coroutines.delay

@Composable
fun RegisterScreen(navController: NavController) {

    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val viewModel: RegisterViewModel = viewModel()
    val success by viewModel.success.collectAsState()
    val error by viewModel.error.collectAsState()

    val (errorNotification, setErrorNotification) = rememberToastState()

    var isPhoneStage by remember { mutableStateOf(true) }

    LaunchedEffect(success) {
        if (success) {
            navController.navigate(Routes.CHATS_LIST) {
                popUpTo(Routes.REGISTER) { inclusive = true }
            }
        }
    }

    LaunchedEffect(error) {
        if (error != null) {
            error?.let { setErrorNotification(it) }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {

        Text(
            text = "Welcome to BatonGram!",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,

            modifier = Modifier.padding(top = 200.dp)
                .align(Alignment.TopCenter)
        )

        Text(
            text = "Please enter your data to register",
            color = MaterialTheme.colorScheme.onPrimary,
            fontSize = 16.sp,

            modifier = Modifier.padding(top = 320.dp)
                .align(Alignment.TopCenter)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 50.dp)
                .padding(top = 100.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            if (isPhoneStage) {

                OutlinedTextField(
                    value = phone,
                    onValueChange = { phone = it },
                    label = { Text("Phone number") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        cursorColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (phone.isNotEmpty()) {
                            if (phone.matches(Regex("89\\d{9}\$")) || phone.matches(Regex("\\+79\\d{9}\$"))) {
                                isPhoneStage = false
                            } else {
                                setErrorNotification("Wrong phone format")
                            }
                        } else {
                            setErrorNotification("Please enter your phone number")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = "Continue")
                }
            } else {
                Box(
                    modifier = Modifier
                        .align(Alignment.Start)
                        .clickable { }
                ) {
                    IconButton(
                        onClick = { isPhoneStage = true },
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                    }
                }
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Password") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                        cursorColor = MaterialTheme.colorScheme.onPrimary,
                    ),
                    visualTransformation = PasswordVisualTransformation(),
                    shape = RoundedCornerShape(12.dp),
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        if (password.isNotEmpty()) {
                            // сразу регаемся, если че с бэка прилетает отказ
                            viewModel.register(phone, password)
                            navController.navigate(Routes.CHATS_LIST)
                        } else {
                            setErrorNotification("Please enter your password")
                        }
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(text = "Register")
                }

                Spacer(modifier = Modifier.height(16.dp))

                TextButton(onClick = {
                    navController.navigate(Routes.LOGIN)
                }) {
                    Text("Log in account", color = MaterialTheme.colorScheme.onSecondary)
                }

            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = {
                navController.navigate(Routes.LOGIN)
            }) {
                Text("Log in account", color = MaterialTheme.colorScheme.onSecondary)
            }
        }
    }

    AppToast(
        message = errorNotification ?: "",
        visible = errorNotification != null
    )

    LaunchedEffect(errorNotification) {
        if (errorNotification != null) {
            delay(3000)
            setErrorNotification(null)
        }
    }
}

