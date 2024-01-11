package com.example.compose_study.ui.screen.feature.data

import com.example.compose_study.ui.screen.feature.component.Category
import java.util.Random

val random = Random()

fun getRandomTabs(): List<Category> {
    val defaultTabs = listOf<Category>(
        Category("커트"),
        Category("펌"),
        Category("염색"),
    )

    val expandedTabs = listOf<Category>(
        Category("커트"),
        Category("펌"),
        Category("염색"),
        Category("메이크업"),
        Category("네일"),
        Category("화장")
    )

    val reducedTabs = listOf<Category>(
        Category("커트"),
    )

    return when (random.nextInt() % 3) {
        0 -> reducedTabs
        1 -> defaultTabs
        else -> expandedTabs
    }
}