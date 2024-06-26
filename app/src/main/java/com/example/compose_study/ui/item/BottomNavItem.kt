package com.example.compose_study.ui.item

import com.example.compose_study.R

sealed class BottomNavItem(
    val title: Int, val icon: Int, val screenRoute: String
) {
    object Paging : BottomNavItem(title = R.string.paging, icon = R.drawable.ic_page, screenRoute = PAGING)
    object Dialog : BottomNavItem(title = R.string.custom_dialog, icon = R.drawable.ic_dialog, screenRoute = DIALOG)
    object Constraint: BottomNavItem(title = R.string.constraint, icon = R.drawable.ic_constraint, screenRoute = CONSTRAINT)
    object ViewPager: BottomNavItem(title = R.string.viewpager, icon = R.drawable.ic_viewpager, screenRoute = VIEWPAGER)
    object Collapsing : BottomNavItem(title = R.string.collapsing, icon = R.drawable.ic_collapsing, screenRoute = COLLAPSING)
    object Slider : BottomNavItem(title = R.string.slider, icon = R.drawable.ic_slider, screenRoute = SLIDER)
    object Todo : BottomNavItem(title = R.string.todo, icon = R.drawable.ic_todo, screenRoute = TODO)
    object Feature : BottomNavItem(title = R.string.feature, icon = R.drawable.ic_feature, screenRoute = FEATURE)
    object Permission : BottomNavItem(title = R.string.permission, icon = R.drawable.ic_permission, screenRoute = PERMISSION)
	object Draw : BottomNavItem(title = R.string.draw, icon = R.drawable.ic_draw, screenRoute = DRAW)
	object Vibrate : BottomNavItem(title = R.string.vibrate, icon = R.drawable.ic_vibrate, screenRoute = VIBRATE)
    object Foldable : BottomNavItem(title = R.string.foldable, icon = R.drawable.ic_foldable, screenRoute = FOLDABLE)
    object StyleBook : BottomNavItem(title = R.string.stylebook, icon = R.drawable.ic_stylebook, screenRoute = STYLEBOOK)
}

const val PAGING = "PAGING"
const val TODO = "TODO"
const val DETAIL = "DETAIL"
const val DIALOG = "DIALOG"
const val CONSTRAINT = "CONSTRAINT"
const val VIEWPAGER = "VIEWPAGER"
const val COLLAPSING = "COLLAPSING"
const val SLIDER = "SLIDER"
const val FEATURE = "FEATURE"
const val PERMISSION = "PERMISSION"
const val DRAW = "DRAW"
const val VIBRATE = "VIBRATE"
const val PHOTO = "PHOTO"
const val SELECT_PHOTO = "SELECT_PHOTO"
const val FOLDABLE = "FOLDABLE"
const val STYLEBOOK = "STYLEBOOK"
