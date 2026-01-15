package com.example.rmp_front.ui_component.screens.settings_screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.rmp_front.R
import android.content.Context
@Composable
fun UserAgreementScreen(
    navController: NavController
) {
    val context = LocalContext.current

    val agreementText by remember {
        mutableStateOf(
            readRawTextFile(context, R.raw.useragreement)
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.navigateUp() },
                modifier = Modifier.size(24.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            }

            Text(
                text = "User Agreement",
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(start = 8.dp)
                    .weight(1f)
            )
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                Text(
                    text = agreementText,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 14.sp,
                    lineHeight = 20.sp,
                    modifier = Modifier.padding(start = 35.dp)
                )
            }
        }
    }
}




fun readRawTextFile(context: Context, resId: Int): String {
    return context.resources.openRawResource(resId)
        .bufferedReader()
        .use { it.readText() }
}
