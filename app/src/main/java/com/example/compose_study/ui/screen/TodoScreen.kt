package com.example.compose_study.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun TodoScreen(
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->
        ConstraintLayoutContent(modifier = Modifier.padding(contentPadding))
    }
}
