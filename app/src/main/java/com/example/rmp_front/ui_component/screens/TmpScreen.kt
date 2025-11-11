package com.example.rmp_front.ui_component.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.rmp_front.viewmodel.TmpViewModel

// временный экранчик для проверки подключения к серверу
@Composable
fun TmpScreen(navController: NavHostController) {
    val viewModel: TmpViewModel = viewModel()
    val message by viewModel.messages.collectAsState()
    val error by viewModel.error.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "ВСЕМ ПРИВ ЭТО /tmp")
        Text(text = "если он работает, то вы успешно подключились к серверу")

        if (error != null) {
            Text(text = "хьюстон, у нас проблема: $error")
        } else if (message != null) {
            message?.let {
                Text(text = "тут выведутся все месаджи с бдшки:")
                Text(text = "пришло: $it")
            }
        } else {
            CircularProgressIndicator()
        }
    }
}
