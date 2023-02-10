package com.example.compose_study.ui.screen

import android.widget.Toast
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext


@Composable
fun TodoScreen(
) {
    Scaffold(
        backgroundColor = MaterialTheme.colors.background,
        modifier = Modifier.fillMaxSize()
    ) { contentPadding ->
//        BottomSheetScreen(modifier = Modifier.padding(contentPadding))
        val context = LocalContext.current
        var showDialog by remember { mutableStateOf(false) }

        TextButton(onClick = { showDialog = true }) {
            Text(text = "Show Dialog")
        }
        if(showDialog) {
            CustomDialogScreen(
                onDismiss = {
                    showDialog = false
                    Toast.makeText(context, "Dismiss Dialog", Toast.LENGTH_SHORT).show()
                },
                onNegativeClick = {
                    showDialog = false
                    Toast.makeText(context, "Negative Dialog", Toast.LENGTH_SHORT).show()
                },
                onPositiveClick = {
                    showDialog = false
                    Toast.makeText(context, "Positive Dialog", Toast.LENGTH_SHORT).show()
                },
                modifier = Modifier.padding(contentPadding)
            )
        }
    }
}
