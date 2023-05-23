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
