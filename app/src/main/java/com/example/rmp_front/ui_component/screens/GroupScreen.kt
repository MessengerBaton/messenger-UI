package com.example.rmp_front.ui_component.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.ui.graphics.RectangleShape
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.rmp_front.ui_component.components.SettingsItem
import com.example.rmp_front.ui_component.navigation.Routes
import com.example.rmp_front.viewmodel.group.GroupViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroupScreen(groupId: String, navController: NavController) {

//    var profileImage by remember { mutableStateOf("") }
//    var name by remember { mutableStateOf("Kitty") }
//    var username by remember { mutableStateOf("@super_kitty") }
//    var phoneNumber by remember { mutableStateOf("89226593565") }
//    var status by remember { mutableStateOf("hi i'm Kitty") }
    var selectedTab by remember { mutableStateOf(0) }

    val viewModel: GroupViewModel = viewModel()
    val group by viewModel.group.collectAsState()

    LaunchedEffect(groupId){
        viewModel.loadGroup(groupId)
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 30.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(400.dp)
                    .clip(RectangleShape)
                    .background(MaterialTheme.colorScheme.primary)
            )

            Box(
                modifier = Modifier.padding(start = 16.dp, top = 10.dp)
                    .align(Alignment.TopStart)
                    .clickable {  }
            ) {
                IconButton(onClick = {navController.popBackStack() },
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = MaterialTheme.colorScheme.onPrimary
                    )
                }
            }
            // Блюр
            GradientBlurOverlay(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .align(Alignment.BottomStart)
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 40.dp, bottom = 14.dp)
                    .align(Alignment.BottomStart),


                ) {
                Text(
                    text = group?.name ?: "",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 16.sp,
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                // я не придумала какие не заебные по исполнению функции туда впихнуть

//                Row(modifier = Modifier
//                    .align(Alignment.BottomEnd)
//                    .padding(end = 40.dp),
//                verticalAlignment = Alignment.CenterVertically,
//                ){
//                    IconButton(
//                        onClick = {navController.popBackStack() } ,
//                        modifier = Modifier
//                            .background(
//                                color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
//                                shape = RoundedCornerShape(12.dp)
//                            )
//                            .padding(2.dp),
//
//                        ) {
//                        Icon(
//                            imageVector = Icons.Default.Search,
//                            contentDescription = "Search in chat",
//                            tint = MaterialTheme.colorScheme.onSecondary
//                        )
//                    }
//                    Spacer(modifier = Modifier.width(10.dp))
//
//                    IconButton(
//                        onClick = { navController.popBackStack() } ,
//                        modifier = Modifier
//                            .background(
//                                color = MaterialTheme.colorScheme.background.copy(alpha = 0.5f),
//                                shape = RoundedCornerShape(12.dp)
//                            )
//                            .padding(2.dp)
//                    ) {
//                        Icon(
//                            imageVector = Icons.Default.MoreHoriz,
//                            contentDescription = "More",
//                            tint = MaterialTheme.colorScheme.onSecondary
//                        )
//                    }
//                }
            }
        }


        Column(modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 40.dp)
        ) {
            Text(
                text = "Members",
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 12.sp,
                modifier = Modifier.padding(start = 20.dp)
            )
//            f
//          что то хуета какая то
//            вообще переделать надо чтоб еще и ава была
            group?.let { groupMembers ->
                for (user in groupMembers.members) {
                    SettingsItem(text = user.name , type = "none", subtitle = "", onClick = {navController.navigate("friend_profile/${user.id}")})
                }
            }


        }


        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 30.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.primary)
                    .height(40.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Text(
                    text = "Photo",
                    color = if (selectedTab == 0) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .clickable { selectedTab = 0 }
                )

                Text(
                    text = "Video",
                    color = if (selectedTab == 1) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .clickable { selectedTab = 1 }
                )
            }

            // ----- GRID -----
            if (selectedTab == 0) {
                Text("Photo", color = MaterialTheme.colorScheme.onSecondary)
//            отображение сетки под фото
            } else {
                Text("Video", color = MaterialTheme.colorScheme.onSecondary)
//            отображение сетки под видео
            }
        }
    }
}
