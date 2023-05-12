package com.example.compose_study.chap1

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose_study.ui.theme.Compose_studyTheme

@Composable
fun ButtonScreen(context: Context) {
    Compose_studyTheme {
        ButtonExample(onButtonClicked = {
            Toast.makeText(context, "Send Clicked.", Toast.LENGTH_SHORT).show()
        })
    }
}

@Composable
fun ButtonExample(onButtonClicked: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Button(onClick = onButtonClicked) {
            Text(text = "Send")
        }

        Button(onClick = onButtonClicked) {
            Icon(imageVector = Icons.Filled.Send, contentDescription = "")
            Text(text = "Send")
        }

        Button(onClick = onButtonClicked) {
            Icon(imageVector = Icons.Filled.Send, contentDescription = "")
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Send")
        }

        Button(
            onClick = onButtonClicked,
            enabled = true,
            border = BorderStroke(10.dp, color = Color.Magenta)
        ) {
            Icon(imageVector = Icons.Filled.Send, contentDescription = "")
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Send")
        }

        Button(
            onClick = onButtonClicked,
            enabled = true,
            border = BorderStroke(10.dp, color = Color.Magenta),
            shape = RoundedCornerShape(10.dp)
        ) {
            Icon(imageVector = Icons.Filled.Send, contentDescription = "")
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Send")
        }

        Button(
            onClick = onButtonClicked,
            enabled = true,
            border = BorderStroke(10.dp, color = Color.Magenta),
            shape = CircleShape
        ) {
            Icon(imageVector = Icons.Filled.Send, contentDescription = "")
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Send")
        }

        Button(
            onClick = onButtonClicked,
            enabled = true,
            border = BorderStroke(10.dp, color = Color.Magenta),
            shape = CircleShape,
            contentPadding = PaddingValues(20.dp)
        ) {
            Icon(imageVector = Icons.Filled.Send, contentDescription = "")
            Spacer(modifier = Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Send")
        }

    }

}

@Preview(showBackground = true)
@Composable
fun ButtonPreview() {
    Compose_studyTheme {
        ButtonExample(onButtonClicked = {})
    }
}