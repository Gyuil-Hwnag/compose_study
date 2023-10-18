package com.example.compose_study.chap1

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_study.ui.theme.ComposeStudyTheme


@Composable
fun SlotAPIScreen() {
    SlotAPIExample()
}

@Composable
fun SlotAPIExample() {
    val checked1 = remember { mutableStateOf(false) }
    val checked2 = remember { mutableStateOf(false) }
    var checked3 by remember { mutableStateOf(false) }
    var checked4 by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxSize()) {
        CheckBoxWithText(checked = checked1, text = "Text 1")
        Spacer(modifier = Modifier.height(8.dp))
        CheckBoxWithText(checked = checked2, text = "Text 2")
        Spacer(modifier = Modifier.height(8.dp))

        CheckBoxWithSlot(
            checked = checked3,
            onCheckedChange = { checked3 = !checked3 }
        ) {
            Text(text = "텍스트 1")
        }
        Spacer(modifier = Modifier.height(8.dp))
        CheckBoxWithSlot(
            checked = checked4,
            onCheckedChange = { checked4 = !checked4 }
        ) {
            Text(text = "텍스트 2")
        }
    }
}

@Composable
fun CheckBoxWithText(checked: MutableState<Boolean>, text: String) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Checkbox(
            checked = checked.value,
            onCheckedChange = { checked.value = it }
        )
        Text(
            modifier = Modifier.clickable { checked.value = !checked.value },
            text = text
        )
    }
}

@Composable
fun CheckBoxWithSlot(
    checked: Boolean,
    onCheckedChange: () -> Unit,
    content: @Composable RowScope.() -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.clickable {
            onCheckedChange()
        }
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = { onCheckedChange() }
        )
        content()
    }
}

@Preview(showBackground = true)
@Composable
fun SlotAPIPreview() {
    ComposeStudyTheme {
        SlotAPIScreen()
    }
}
