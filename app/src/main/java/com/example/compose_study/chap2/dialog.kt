package com.example.compose_study.chap2

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_study.ui.theme.Compose_studyTheme


@Composable
fun DialogScreen() {
    DialogExample()
}

@Composable
fun DialogExample() {
    var openDialog by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column {
        Button(onClick = { openDialog = true }) {
            Text(text = "Open Dialog")
        }
        Text(text = "Open Dialog Count : $counter")
    }

    if (openDialog) {
        AlertDialog(
            onDismissRequest = { openDialog = false },
            confirmButton = {
                Button(
                    onClick = {
                        counter++
                        openDialog = false
                    }
                ) {
                    Text(text = "Plus Count")
                }
            },
            dismissButton = {
                Button(
                    onClick = { openDialog = false }
                ) {
                    Text(text = "Cancel")
                }
            },
            title = { Text(text = "Is Counter Plus") },
            text = { Text(text = "Push Plus Count Button") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DialogPreview() {
    Compose_studyTheme {
        DialogScreen()
    }
}
