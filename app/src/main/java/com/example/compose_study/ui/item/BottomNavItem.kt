package com.example.compose_study.ui.item

import com.example.compose_study.R

sealed class BottomNavItem(
    val title: Int, val icon: Int, val screenRoute: String
) {
    object Paging : BottomNavItem(title = R.string.paging, icon = R.drawable.ic_page, screenRoute = PAGING)
    object Todo : BottomNavItem(title = R.string.todo, icon = R.drawable.ic_todo, screenRoute = TODO)
}

const val PAGING = "PAGING"
const val TODO = "TODO"
const val DETAIL = "DETAIL"

