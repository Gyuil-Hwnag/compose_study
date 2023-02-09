package com.example.compose_study.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose_study.ui.screen.DetailScreen
import com.example.compose_study.ui.screen.HomeScreen
import com.example.compose_study.ui.screen.HomeViewModel

@Composable
fun NavigationController() {
    val (value: String, setValue: (String) -> Unit) = remember { mutableStateOf("") }

    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "Home") {
        composable("Home") {
//            val parentEntry = remember(it) {
//                navController.getBackStackEntry("Parent")
//            }
//            val parentViewModel = viewModel<HomeViewModel>(parentEntry)
//            parentViewModel
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
