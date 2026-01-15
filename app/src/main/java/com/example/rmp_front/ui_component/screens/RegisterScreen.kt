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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.rmp_front.data.RegisterStep
import com.example.rmp_front.ui_component.components.AppToast
import com.example.rmp_front.ui_component.components.rememberToastState
import com.example.rmp_front.ui_component.navigation.Routes
import com.example.rmp_front.viewmodel.register.RegisterViewModel
import kotlinx.coroutines.delay

@Composable
fun RegisterScreen(navController: NavController) {

    val context = LocalContext.current

    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val viewModel: RegisterViewModel = viewModel()
    val user by viewModel.user.collectAsState()
    val step by viewModel.step.collectAsState()
    val error by viewModel.error.collectAsState()

    val (errorNotification, setErrorNotification) = rememberToastState()



    LaunchedEffect(user) {
        if (user != null) {
            navController.navigate(Routes.CHATS_LIST) {
                popUpTo(Routes.REGISTER) { inclusive = true }
            }
        }
    }


    Box(modifier = Modifier.fillMaxSize()
        .background(MaterialTheme.colorScheme.background)) {

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

            when (step) {

                RegisterStep.PHONE -> {

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
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = { viewModel.checkPhoneNumber(phone) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(text = "Continue")
                    }
                }

                RegisterStep.PASSWORD -> {

                    IconButton(onClick = { viewModel.backToPhone() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back",
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
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
                        shape = RoundedCornerShape(18.dp),
                        modifier = Modifier.fillMaxWidth()
                    )

                    Spacer(modifier = Modifier.height(24.dp))

                    Button(
                        onClick = {
                            viewModel.register(phone = phone, password = password, context = context)
                        },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = MaterialTheme.colorScheme.primary
                        )
                    ) {
                        Text(text = "Register")
                    }
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

