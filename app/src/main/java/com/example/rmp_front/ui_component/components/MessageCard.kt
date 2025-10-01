package com.example.rmp_front.ui_component.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rmp_front.AppColors

@Composable
fun MessageCard(text: String, time: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 4.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = AppColors.MessageCard)
    ) {
        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                color = AppColors.TextPrimary,
                fontSize = 16.sp,
                modifier = Modifier.weight(1f)
            )
            Text(
                text = time,
                color = AppColors.TextSecondary,
                fontSize = 12.sp,
                textAlign = TextAlign.End
            )
        }
    }
}