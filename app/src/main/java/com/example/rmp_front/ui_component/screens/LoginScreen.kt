package com.example.rmp_front.ui_component.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
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
import com.example.rmp_front.viewmodel.login.LoginViewModel

import kotlinx.coroutines.delay


@Composable
fun LoginScreen(navController: NavController) {

    val viewModel: LoginViewModel = viewModel()
    val response by viewModel.response.collectAsState()
    val error by viewModel.error.collectAsState()

    val (errorNotification, setErrorNotification) = rememberToastState()

    var phone by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(response) {
        if (response == true) {
            navController.navigate(Routes.CHATS_LIST)
        }
    }

    LaunchedEffect(error) {
        if (error != null) {
            setErrorNotification(error!!)
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
            text = "Please enter your data to log in",
            color = MaterialTheme.colorScheme.onPrimary,
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
                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                    cursorColor = MaterialTheme.colorScheme.onPrimary,
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
                    // проверка в бд в теории как то так
                    viewModel.checkPersonData(phone, password)

                },
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {
                Text(text = "Log In")
            }

            Spacer(modifier = Modifier.height(16.dp))

            TextButton(onClick = {
                navController.navigate(Routes.REGISTER)
            }) {
                Text("Create account", color = MaterialTheme.colorScheme.onSecondary)
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
