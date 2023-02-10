package com.example.compose_study.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun CustomDialogScreen(
    onDismiss: () -> Unit,
    onNegativeClick: () -> Unit,
    onPositiveClick: () -> Unit,
    properties: DialogProperties = DialogProperties(),
    modifier: Modifier = Modifier
) {
    // onDissRequest = onDiss를 하면 외부 클릭 허용O, Unit으로 할 경우 외부 클릭 허용X
    Dialog(onDismissRequest = {}, properties = properties) {
        Surface(
            modifier = modifier.fillMaxWidth().padding(horizontal = 18.dp, vertical = 12.dp),
            shape = RoundedCornerShape(12.dp),
            color = Color.White
        ) {
            DialogContent(
                onDismiss = onDismiss,
                onNegativeClick = onNegativeClick,
                onPositiveClick = onPositiveClick
            )
        }
    }
}

@Composable
fun DialogContent(
    onDismiss: () -> Unit,
    onNegativeClick: () -> Unit,
    onPositiveClick: () -> Unit
) {
    Column {
        Spacer(modifier = Modifier
            .height(12.dp)
            .fillMaxWidth())

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth()
                .padding(8.dp),
            text = "Dialog",
            textAlign = TextAlign.Center,
            fontSize = 12.sp,
            lineHeight = 17.sp
        )

        Spacer(modifier = Modifier
            .height(12.dp)
            .fillMaxWidth())

        Button(onClick = { onDismiss() }, modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(horizontal = 20.dp), shape = RoundedCornerShape(24.dp))
        {
            Text(text = "Dismiss", fontSize = 16.sp)
        }

        Spacer(modifier = Modifier
            .height(12.dp)
            .fillMaxWidth())

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {

            TextButton(
                onClick = { onNegativeClick() }
            ) {
                Text(text = "취소", color = Color.Black)
            }

            Spacer(modifier = Modifier.width(4.dp))

            TextButton(
                onClick = { onPositiveClick() },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Black)
            ) {
                Text(text = "확인", color = Color.White)
            }
        }
    }
}
