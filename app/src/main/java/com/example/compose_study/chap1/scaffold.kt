@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.compose_study.chap1

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
fun ScaffoldScreen() {
    ScaffoldExample()
}

@Composable
fun CheckBoxWithContent(
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldExample() {
    var checked by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Scaffold App") },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Image(imageVector = Icons.Filled.ArrowBack, contentDescription = "")
                    }
                }
            ) 
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {}) {
                Image(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = "")
            }
        }
    ) { paddingValue ->
        Surface(modifier = Modifier
            .padding(8.dp)
            .padding(paddingValue)) {
            CheckBoxWithContent(
                checked = checked,
                onCheckedChange = { checked = !checked }
            ) {
                Text(text = "Compose CheckBox")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ScaffoldPreview() {
    ComposeStudyTheme {
        ScaffoldScreen()
    }
}
