package com.example.compose_study.chap1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_study.ui.theme.ComposeStudyTheme


@Composable
fun CheckBoxScreen() {
    CheckBoxExample()
}

@Composable
fun CheckBoxExample() {
    Column(modifier = Modifier.fillMaxSize()) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = false, onCheckedChange = {})
            Text(text = "Hello Android")
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            var checked = false
            Checkbox(checked = checked, onCheckedChange = { checked = !checked })
            Text(text = "Hello Android")
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            /**
             * destruction : 비구조화, 반구조화, 구조분해
             * val (a, b) = listOf(2, 3)
             **/

            val remeberChecked = remember { mutableStateOf(false) }
            Checkbox(checked = remeberChecked.value, onCheckedChange = { remeberChecked.value = !remeberChecked.value })
            Text(text = "Hello Android")
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            /**
             * Delegate Properties
             **/

            var delegateChecked by remember { mutableStateOf(false) }
            Checkbox(checked = delegateChecked, onCheckedChange = { delegateChecked = !delegateChecked })
            Text(text = "Hello Android")
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            val (getChecked, setChecked) = remember { mutableStateOf(false) }
            Checkbox(checked = getChecked, onCheckedChange = setChecked)
            Text(text = "Hello Android")
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            val (getter, setter) = remember { mutableStateOf(false) }
            Checkbox(checked = getter, onCheckedChange = setter)
            Text(text = "Hello Android", modifier = Modifier.clickable { setter(!getter) })
        }

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            val (getter, setter) = remember { mutableStateOf(false) }
            Checkbox(checked = getter, onCheckedChange = setter)
            Text(text = if (getter) "Clicked" else "Not Clicked", modifier = Modifier.clickable { setter(!getter) })
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CheckBoxPreview() {
    ComposeStudyTheme {
        CheckBoxScreen()
    }
}
