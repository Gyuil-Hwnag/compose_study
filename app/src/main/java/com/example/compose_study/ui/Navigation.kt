package com.example.compose_study.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_study.ui.screen.DetailScreen
import com.example.compose_study.ui.screen.HomeScreen

@Composable
fun NavigationController() {
    val (value: String, setValue: (String) -> Unit) = remember { mutableStateOf("") }

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
            HomeScreen(
                toDetail = {
                    setValue(it)
                    navController.navigate("Detail")
                }
            )
        }

        composable("Detail") {
            DetailScreen(
                id = value,
                toBack = { navController.popBackStack() }
            )
        }
    }
}
