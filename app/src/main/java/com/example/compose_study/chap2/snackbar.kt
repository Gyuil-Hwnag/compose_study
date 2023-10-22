package com.example.compose_study.chap2

import android.annotation.SuppressLint
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarResult
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_study.ui.theme.ComposeStudyTheme
import kotlinx.coroutines.launch


@Composable
fun SnackBarScreen() {
    SnackBarExample()
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun SnackBarExample() {
    var counter by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState
    ) {
        Button(
            onClick = {
                counter++
                coroutineScope.launch {
                    val result = scaffoldState.snackbarHostState.showSnackbar(
                        message = "카운터 : $counter 입니다.",
                        actionLabel = "닫기",
                        duration = SnackbarDuration.Short
                    )
                    when(result) {
                        SnackbarResult.Dismissed -> {}
                        SnackbarResult.ActionPerformed -> {}
                    }
                }
            }
        ) {
            Text(text = "Plus 1")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SnackBarPreview() {
    ComposeStudyTheme {
        SnackBarScreen()
    }
}
