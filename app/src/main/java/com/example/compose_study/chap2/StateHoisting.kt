package com.example.compose_study.chap2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_study.ui.theme.Compose_studyTheme


@Composable
fun StateHoistingScreen() {
    StateHoistingExample()
}

@Composable
fun StateHoistingExample() {
    var number by rememberSaveable { mutableStateOf("23") }
    var square by rememberSaveable { mutableStateOf((23 * 3.306).toString()) }

//    Column(
//        modifier = Modifier.padding(16.dp)
//    ) {
//        OutlinedTextField(
//            value = number,
//            onValueChange = {
//                if (it.isBlank()) {
//                    number = ""
//                    square = ""
//                }
//                val numericValue = it.toFloatOrNull() ?: return@OutlinedTextField
//                number = it
//                square = (numericValue * 3.306).toString()
//            },
//            label = { Text(text = "평") }
//        )
//        OutlinedTextField(
//            value = square,
//            onValueChange = { },
//            label = { Text(text = "제곱미터") }
//        )
//    }

    StateHoistingStateless(number = number, square = square) {
        if (it.isBlank()) {
            number = ""
            square = ""
            return@StateHoistingStateless
        }
        val numericValue = it.toFloatOrNull() ?: return@StateHoistingStateless
        number = it
        square = (numericValue * 3.306).toString()
    }
}

@Composable
fun StateHoistingStateless(
    number: String,
    square: String,
    onNumberChange: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            value = number,
            onValueChange = onNumberChange,
            label = { Text(text = "평") }
        )
        OutlinedTextField(
            value = square,
            onValueChange = {},
            label = { Text(text = "제곱미터") }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun StateHoistingPreview() {
    Compose_studyTheme {
        StateHoistingScreen()
    }
}
