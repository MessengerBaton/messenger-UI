package com.example.rmp_front.ui_component.screens

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.CameraAlt
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.example.rmp_front.ui_component.components.ChangeItem
import com.example.rmp_front.viewmodel.MainViewModel

@Composable
fun ChangeProfileScreen(navController: NavController, mainViewModel: MainViewModel) {
    var profileImageUri by remember { mutableStateOf<Uri?>(null) }
    var name by remember { mutableStateOf("") }
    var username by remember { mutableStateOf("") }
    var phoneNumber by remember { mutableStateOf("") }
    var status by remember { mutableStateOf("") }

    val user by mainViewModel.user.collectAsState()

    val galleryLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri ->
            profileImageUri = uri
        }
    )

    LaunchedEffect(user) {
        user?.let {
            name = it.name ?: ""
            username = it.nick ?: ""
            phoneNumber = it.phone ?: ""
            status = it.about ?: ""

            if (!it.avatarUrl.isNullOrEmpty()) {
                try {
                    profileImageUri = Uri.parse(it.avatarUrl)
                } catch (e: Exception) {
                }
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.Default.ArrowBack, "Back", tint = MaterialTheme.colorScheme.onPrimary)
            }

            Text("Edit Profile", color = MaterialTheme.colorScheme.onPrimary, fontSize = 20.sp)

            Button(
                onClick = {
                    mainViewModel.saveUser(
                        user?.copy(
                            name = name,
                            nick = username,
                            phone = phoneNumber,
                            about = status,
                            avatarUrl = profileImageUri?.toString()
                        ) ?: return@Button
                    )
                    navController.popBackStack()
                }
            ) {
                Text("Save")
            }
        }

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 32.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .size(240.dp)
                    .clip(CircleShape),
                contentAlignment = Alignment.Center
            ) {
                val currentImage = remember(profileImageUri, user?.avatarUrl) {
                    if (profileImageUri != null) {
                        profileImageUri
                    } else if (!user?.avatarUrl.isNullOrEmpty()) {
                        Uri.parse(user?.avatarUrl)
                    } else {
                        null
                    }
                }

                if (currentImage != null) {
                    Image(
                        painter = rememberAsyncImagePainter(currentImage),
                        contentDescription = "Profile",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )
                } else {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(MaterialTheme.colorScheme.primary),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            Icons.Default.Person,
                            "Add photo",
                            tint = MaterialTheme.colorScheme.onPrimary,
                            modifier = Modifier.size(100.dp)
                        )
                    }
                }
            }

            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .offset(x = 16.dp, y = 0.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable { galleryLauncher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    Icons.Default.CameraAlt,
                    "Change photo",
                    tint = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Column(
            modifier = Modifier.padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            ChangeItem(value = name, onValueChange = { name = it }, text = "Name")
            ChangeItem(value = username, onValueChange = { username = it }, text = "Username")
            ChangeItem(value = phoneNumber, onValueChange = { phoneNumber = it }, text = "Phone")
            ChangeItem(value = status, onValueChange = { status = it }, text = "Bio")
        }
    }
}