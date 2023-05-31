package com.example.compose_study.ui.screen.feature.component

fun lerp(start: Float, stop: Float, fraction: Float): Float {
    return (1 - fraction) * start + fraction * stop
}

fun List<Any>.infiniteLoopInitPage(): Int {
    val size = this.size
    return ((Int.MAX_VALUE / size)/2) * size
}