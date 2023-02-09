package com.example.compose_study.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.compose_study.ui.screen.BottomNavigationScreen
import com.example.compose_study.ui.theme.Compose_studyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Compose_studyTheme {
                MainScreenView()
            }
        }
    }
}

@Composable
fun MainScreenView(){
    val navController = rememberNavController ()
    Scaffold (
        modifier = Modifier.fillMaxSize(),
        bottomBar = { BottomNavigationScreen(navController = navController) }
    ) { contentPadding ->
        NavigationGraph(navController = navController, modifier = Modifier.padding(contentPadding))
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Compose_studyTheme {
    }
}
