package com.example.compose_study.chap2

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_study.ui.theme.Compose_studyTheme


@Composable
fun DropDownMenuScreen() {
    DropDownMenuExample()
}

@Composable
fun DropDownMenuExample() {
    var expandDropDownMenu by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column {
        Button(
            onClick = { expandDropDownMenu = true }
        ) {
            Text(text = "드롭다운 메뉴 열기")
        }
        Text(text = "COUNTER : $counter")
    }

    DropdownMenu(
        expanded = expandDropDownMenu,
        onDismissRequest = {
            expandDropDownMenu = false
        }
    ) {
        DropdownMenuItem(
            text = { Text(text = "증가") },
            onClick = { counter++ }
        )
        DropdownMenuItem(
            text = { Text(text = "감소") },
            onClick = { counter-- }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DropDownMenuPreview() {
    Compose_studyTheme {
        DropDownMenuScreen()
    }
}
