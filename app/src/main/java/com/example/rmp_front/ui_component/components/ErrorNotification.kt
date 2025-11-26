package com.example.rmp_front.ui_component.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.rmp_front.AppColors
@Composable
fun AppToast(
    message: String,
    visible: Boolean,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp)
                .padding(horizontal = 80.dp)
                .wrapContentHeight()
                .background(
                    AppColors.ErrorColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .then(modifier),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = message,
                color = AppColors.TextPrimary,
                fontSize = 16.sp,
                modifier = Modifier.padding(all = 10.dp)
            )
        }
    }
}


@Composable
fun rememberToastState(): Pair<String?, (String?) -> Unit> {
    val message = remember { mutableStateOf<String?>(null) }
    return message.value to { new -> message.value = new }
}
