package com.example.compose_study.ui

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.compose_study.ui.screen.foldable.FoldableScreen
import com.example.compose_study.ui.screen.home.HomeScreen
import com.example.compose_study.ui.screen.more.More
import com.example.compose_study.ui.screen.permission.PermissionScreen
import com.example.compose_study.ui.screen.photo.PhotoScreen
import com.example.compose_study.ui.screen.photo.select.SelectPhotoScreen
import com.example.compose_study.ui.screen.slider.SliderScreen
import com.example.compose_study.ui.screen.stylebook.StyleBookScreen
import com.example.compose_study.ui.screen.vibrate.VibrateScreen
import com.example.compose_study.ui.screen.viewpager.ViewpagerScreen
import com.example.compose_study.ui.screen.viewpagerwithtabbar.ViewPagerWithTabBarScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun NavigationGraph(
    modifier: Modifier,
    navController: NavHostController
) {
    val (value: String, setValue: (String) -> Unit) = remember { mutableStateOf("") }
    val (selectPhoto: String, setSelectPhoto: (String) -> Unit) = remember { mutableStateOf("") }
    val (selectPhotos: List<String>, setSelectPhotos: (List<String>) -> Unit) = remember { mutableStateOf(emptyList<String>()) }

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
            PermissionScreen(
                toPhotoPicker = { navController.navigate(PHOTO) },
                toSelectPhoto = { navController.navigate(SELECT_PHOTO) },
                selectedPhoto = selectPhoto,
                selectedPhotos = selectPhotos
            )
        }
        composable(BottomNavItem.Draw.screenRoute) {
            DrawScreen()
        }
        composable(BottomNavItem.Vibrate.screenRoute) {
            VibrateScreen()
        }
        composable(BottomNavItem.Foldable.screenRoute) {
            FoldableScreen(
                toDetail = {
                    setValue(it)
                    navController.navigate(DETAIL)
                }
            )
        }
        composable(BottomNavItem.StyleBook.screenRoute) {
            StyleBookScreen()
        }
        composable(PHOTO) {
            PhotoScreen(
                toBack = { navController.popBackStack() },
                selectPhoto = {
                    navController.popBackStack()
                    setSelectPhoto(it)
                }
            )
        }
        composable(SELECT_PHOTO) {
            SelectPhotoScreen(
                toBack = { navController.popBackStack() },
                selectPhotos = {
                    navController.popBackStack()
                    setSelectPhotos(it)
                }
            )
        }
    }
}
