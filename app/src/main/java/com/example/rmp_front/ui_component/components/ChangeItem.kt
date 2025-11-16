package com.example.rmp_front.ui_component.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.rmp_front.AppColors



@Composable
fun ChangeItem(
    value: String,
    onValueChange: (String) -> Unit,
    text: String,
    hint: String = "Put your $text",
) {
    Box(modifier = Modifier.padding(horizontal = 30.dp)) {


        OutlinedTextField(
            value = value,
            onValueChange = onValueChange,
            label = {
                if (value.isNotEmpty()) {
                    Text(text, color = AppColors.PlaceholderText)
                } else {
                    Text(text = hint, color = AppColors.PlaceholderText)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = AppColors.InputBackground,
                unfocusedContainerColor = AppColors.InputBackground,
                disabledContainerColor = AppColors.InputBackground,
                focusedBorderColor = AppColors.TextSecondary,
            ),
            textStyle = TextStyle(color = AppColors.InputText),
            shape = RoundedCornerShape(12.dp)
        )
    }

}