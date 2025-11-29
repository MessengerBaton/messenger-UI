package com.example.rmp_front.ui_component.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rmp_front.ui.theme.LocalAppState

@Composable
fun SettingsItem(
    text: String,
    type: String,
    subtitle: String
) {
    val appState = LocalAppState.current
    var isChecked by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp, horizontal = 10.dp)
            .clickable { /* навигация */ },
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primary)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            if (type == "default") {
                Text(
                    text = text,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                        .padding(start = 8.dp)
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Next",
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            } else if (type == "sub"){
                Text(
                    text = text,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                        .padding(start = 8.dp)
                )
                Text(
                    text = subtitle,
                    color = MaterialTheme.colorScheme.onSecondary,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Next",
                    tint = MaterialTheme.colorScheme.onSecondary
                )
            } else if (type == "none"){
                Text(
                    text = text,
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 16.sp,
                    modifier = Modifier.weight(1f)
                        .padding(start = 8.dp)
                )
            }
        }
    }
}