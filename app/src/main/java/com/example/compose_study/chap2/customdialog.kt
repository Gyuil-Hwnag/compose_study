package com.example.compose_study.chap2

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.compose_study.ui.theme.Compose_studyTheme


@Composable
fun CustomDialogScreen() {
    CustomDialogExample()
}

@Composable
fun CustomDialogExample() {
    var openDialog by remember { mutableStateOf(false) }
    var counter by remember { mutableStateOf(0) }

    Column {
        Button(
            onClick = { openDialog = true }
        ) {
            Text(text = "Open Dialog")
        }
        Text(text = "Open Dialog Count : $counter")
    }

    if (openDialog) {
        Dialog(
            onDismissRequest = {
                // 외부 클릭시 Dialog 닫히도록
                openDialog = false
            }
        ) {
            DialogItem(
                onPlusClicked = { counter++ },
                onMinusClicked = { counter-- },
                onCloseDialog = { openDialog = false }
            )
        }
    }
}

@Composable
fun DialogItem(onPlusClicked: () -> Unit, onMinusClicked: () -> Unit, onCloseDialog: () -> Unit) {
    Surface {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                modifier = Modifier.padding(16.dp),
                fontSize = 14.sp,
                text = "버튼을 클릭해주세요.\n * +1 버튼을 클릭하면 값이 증가됩니다.\n * -1 버튼을 클릭하면 값이 감소합니다."
            )
            Row(modifier = Modifier.align(Alignment.End)) {
                Button(
                    modifier = Modifier.padding(4.dp),
                    onClick = { onCloseDialog() }
                ) {
                    Text(text = "종료")
                }
                Button(
                    modifier = Modifier.padding(4.dp),
                    onClick = { onPlusClicked() }
                ) {
                    Text(text = "+1")
                }
                Button(
                    modifier = Modifier.padding(4.dp),
                    onClick = { onMinusClicked() }
                ) {
                    Text(text = "-1")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CustomDialogPreview() {
    Compose_studyTheme {
        CustomDialogScreen()
    }
}
