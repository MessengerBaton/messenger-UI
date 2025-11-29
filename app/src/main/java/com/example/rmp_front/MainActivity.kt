package com.example.rmp_front

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.example.rmp_front.ui.theme.RMP_FRONTTheme
import com.example.rmp_front.ui_component.components.BottomNavigationBar
import com.example.rmp_front.ui_component.navigation.NavGraph
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            var darkTheme by remember { mutableStateOf(true) }
            RMP_FRONTTheme (darkTheme = darkTheme){
                MainApp(
                    darkTheme = darkTheme,
                    onThemeChange = {darkTheme = it}
                )
            }
        }
    }
}

@Composable
fun MainApp(
    darkTheme: Boolean,
    onThemeChange: (Boolean) -> Unit
) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) { paddingValues ->
        NavGraph(
            navController = navController,
            darkTheme = darkTheme,
            onThemeChange = onThemeChange,
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues))
    }
}