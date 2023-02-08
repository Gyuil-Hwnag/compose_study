package com.example.compose_study.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize().background(Color.White.copy(0.1f)),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}
