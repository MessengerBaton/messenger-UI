package com.example.rmp_front.ui_component.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun MessageCard(
    text: String,
    time: String,
    isOwn: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 6.dp),
        horizontalArrangement = if (isOwn) Arrangement.End else Arrangement.Start
    ) {
        Column(
            modifier = Modifier
                .wrapContentWidth()
                .widthIn(max = 280.dp)
                .background(
                    if (isOwn) MaterialTheme.colorScheme.primary else Color(0xFFE5E5EA),
                    shape = MaterialTheme.shapes.medium
                )
                .padding(10.dp)
        ) {
            Text(
                text = text,
                color = if (isOwn) Color.White else Color.Black
            )

            Spacer(modifier = Modifier.height(4.dp))

            Row(
                Modifier.align(alignment = Alignment.End)
            ) {
                Text(
                    text = time,
                    color = if (isOwn) Color.White.copy(0.7f) else Color.Gray,
                    fontSize = MaterialTheme.typography.labelSmall.fontSize
                )
            }
        }
    }
}

