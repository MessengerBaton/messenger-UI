package com.example.rmp_front.ui_component.components


import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp



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
                    Text(text, color = MaterialTheme.colorScheme.onTertiary)
                } else {
                    Text(text = hint, color = MaterialTheme.colorScheme.onTertiary)
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedContainerColor = MaterialTheme.colorScheme.background,
                unfocusedContainerColor = MaterialTheme.colorScheme.background,
                disabledContainerColor = MaterialTheme.colorScheme.background,
                focusedBorderColor = MaterialTheme.colorScheme.onSecondary,
            ),
            textStyle = TextStyle(color = MaterialTheme.colorScheme.onPrimary),
            shape = RoundedCornerShape(18.dp)
        )
    }

}