package com.example.compose_study.ui.screen.dialog

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.compose_study.ui.screen.dialog.BottomSheetScreen

@Composable
fun CustomDialogScreen(
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->
        BottomSheetScreen(modifier = Modifier.padding(contentPadding))
    }
}
