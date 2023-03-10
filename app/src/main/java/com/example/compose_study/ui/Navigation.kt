package com.example.compose_study.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose_study.ui.item.BottomNavItem
import com.example.compose_study.ui.item.DETAIL
import com.example.compose_study.ui.screen.*
import com.example.compose_study.ui.screen.collapsing.CollapsingToolBarScreen
import com.example.compose_study.ui.screen.detail.DetailScreen
import com.example.compose_study.ui.screen.dialog.CustomDialogScreen
import com.example.compose_study.ui.screen.home.HomeScreen
import com.example.compose_study.ui.screen.todo.TodoScreen
import com.example.compose_study.ui.screen.viewpager.ViewpagerScreen

@Composable
fun NavigationGraph(modifier: Modifier, navController: NavHostController) {
    val (value: String, setValue: (String) -> Unit) = remember { mutableStateOf("") }

    NavHost(
        navController = navController,
        startDestination = BottomNavItem.Paging.screenRoute,
        modifier = modifier
    ) {
        composable(BottomNavItem.Paging.screenRoute) {
            HomeScreen(
                toDetail = {
                    setValue(it)
                    navController.navigate(DETAIL)
                }
            )
        }
        composable(DETAIL) {
            DetailScreen(
                id = value,
                toBack = { navController.popBackStack() }
            )
        }
        composable(BottomNavItem.Dialog.screenRoute) {
            CustomDialogScreen()
        }
        composable(BottomNavItem.Constraint.screenRoute) {
            ConstraintScreen()
        }
        composable(BottomNavItem.ViewPager.screenRoute) {
            ViewpagerScreen()
        }
        composable(BottomNavItem.Collapsing.screenRoute) {
            CollapsingToolBarScreen()
        }
        composable(BottomNavItem.Todo.screenRoute) {
            TodoScreen()
        }
    }
}
