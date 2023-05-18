package com.example.compose_study.chap1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_study.ui.theme.Compose_studyTheme


@Composable
fun TextFieldScreen() {
    TextFieldExample()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextFieldExample() {
    var name by remember { mutableStateOf("Tom") }
    Column(modifier = Modifier.fillMaxSize()) {
        TextField(value = "Tom", onValueChange = {})
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Hello Android")

        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = name, onValueChange = { name = it })
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Hello $name")

        Spacer(modifier = Modifier.height(16.dp))

        TextField(value = name, onValueChange = { name = it }, label = { Text(text = "이름을 입력하세요.") })
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Hello $name")

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(value = name, onValueChange = { name = it }, label = { Text(text = "이름을 입력하세요.") })
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Hello $name")

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview(showBackground = true)
@Composable
fun TextFieldPreview() {
    Compose_studyTheme {
        TextFieldScreen()
    }
}
