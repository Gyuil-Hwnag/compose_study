package com.example.compose_study.ui.screen.main

import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.compose_study.ui.item.BottomNavItem


@Composable
fun BottomNavigationScreen(navController: NavController) {
    val items = listOf(
        BottomNavItem.Paging,
        BottomNavItem.Dialog,
        BottomNavItem.Collapsing,
        BottomNavItem.Todo,
        BottomNavItem.Feature,
        BottomNavItem.Permission,
        BottomNavItem.Vibrate,
        BottomNavItem.Foldable,
        BottomNavItem.StyleBook
    )
    BottomNavigation(
        backgroundColor = Color.Blue,
        contentColor = Color.White
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = {
                    Icon(painter = painterResource(id = item.icon), contentDescription = stringResource(id = item.title))
                },
                label = {
                    Text(
                        modifier = Modifier.height(12.dp),
                        text = stringResource(id = item.title),
                        fontSize = 9.sp
                    )
                },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screenRoute,
                onClick = {
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}
