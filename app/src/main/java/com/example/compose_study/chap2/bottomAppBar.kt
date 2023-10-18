package com.example.compose_study.chap2

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomAppBar
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_study.ui.theme.ComposeStudyTheme
import kotlinx.coroutines.launch

@Composable
fun BottomAppBarScreen() {
    BottomAppBarExample()
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun BottomAppBarExample() {
    val scaffoldState = rememberScaffoldState()
    val coroutineScope = rememberCoroutineScope()
    var count by remember { mutableStateOf(0) }

    LaunchedEffect(key1 = scaffoldState.snackbarHostState) {
        coroutineScope.launch {
            scaffoldState.snackbarHostState.showSnackbar("Hello")
        }
    }

    Scaffold(
        scaffoldState = scaffoldState,
        bottomBar = {
            BottomAppBar {
                Text(text = "Hello")
                Button(
                    onClick = {
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("Hello!!")
                        }
                    }
                ) {
                    Text(text = "Say Hello")
                }
                Button(
                    onClick = {
                        count++
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("더해서 $count 입니다.")
                        }
                    }
                ) {
                    Text(text = "더하기")
                }
                Button(
                    onClick = {
                        count--
                        coroutineScope.launch {
                            scaffoldState.snackbarHostState.showSnackbar("빼서 $count 입니다.")
                        }
                    }
                ) {
                    Text(text = "빼기")
                }
            }
        }
    ) {
//        Text(text = "")
//        LaunchedEffect(key1 = scaffoldState.snackbarHostState) {
//            coroutineScope.launch {
//                scaffoldState.snackbarHostState.showSnackbar("Hello!!")
//            }
//        }

        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "카운터는 ${count}회 입니다."
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun BottomAppBarPreview() {
    ComposeStudyTheme {
        BottomAppBarScreen()
    }
}
