package com.example.compose_study.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.compose_study.ui.item.*
import com.example.compose_study.ui.screen.*
import com.example.compose_study.ui.screen.collapsing.CollapsingToolBarScreen
import com.example.compose_study.ui.screen.detail.DetailScreen
import com.example.compose_study.ui.screen.dialog.CustomDialogScreen
import com.example.compose_study.ui.screen.draw.DrawScreen
import com.example.compose_study.ui.screen.feature.FeatureScreen
import com.example.compose_study.ui.screen.home.HomeScreen
import com.example.compose_study.ui.screen.more.More
import com.example.compose_study.ui.screen.permission.PermissionScreen
import com.example.compose_study.ui.screen.slider.SliderScreen
import com.example.compose_study.ui.screen.todo.TodoScreen
import com.example.compose_study.ui.screen.viewpager.ViewpagerScreen
import com.example.compose_study.ui.screen.viewpagerwithtabbar.ViewPagerWithTabBarScreen

@Composable
fun NavigationGraph(
    modifier: Modifier,
    navController: NavHostController
) {
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
        composable(BottomNavItem.Slider.screenRoute) {
            SliderScreen()
        }
        composable(BottomNavItem.Todo.screenRoute) {
            ViewPagerWithTabBarScreen(
                onMoreClicked = {
                    when(it) {
                        is More.Profile -> navController.navigate(DETAIL)
                        is More.Notification -> navController.navigate(DIALOG)
                        is More.Information -> navController.navigate(VIEWPAGER)
                        is More.Question -> navController.navigate(COLLAPSING)
                    }
                }
            )
        }
        composable(BottomNavItem.Feature.screenRoute) {
            FeatureScreen()
        }

        composable(BottomNavItem.Permission.screenRoute) {
            PermissionScreen()
        }
        composable(BottomNavItem.Draw.screenRoute) {
            DrawScreen()
        }
    }
}
