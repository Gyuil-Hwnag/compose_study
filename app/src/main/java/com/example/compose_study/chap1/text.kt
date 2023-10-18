package com.example.compose_study.chap1

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.compose_study.ui.theme.ComposeStudyTheme


@Composable
fun TextScreen() {
    TextExample("Android")
}

@Composable
fun TextExample(text: String) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Hello $text!")
        Text(color = Color.Red, text = "Hello $text!")
        Text(color = Color(0xffff9944), text = "Hello $text!")
        Text(color = Color.Red, text = "Hello $text!", fontSize = 30.sp)
        Text(color = Color.Red, text = "Hello $text!", fontSize = 30.sp, fontWeight = FontWeight.Bold)
        Text(
            color = Color.Red,
            text = "Hello $text!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif
        )
        Text(
            color = Color.Red,
            text = "Hello $text!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 2.sp
        )
        Text(
            color = Color.Red,
            text = "Hello $text!\nHello $text!\nHello $text!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 2.sp,
            maxLines = 2
        )
        Text(
            color = Color.Red,
            text = "Hello $text!\nHello $text!\nHello $text!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 2.sp,
            maxLines = 2,
            textDecoration = TextDecoration.Underline
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            color = Color.Red,
            text = "Hello $text!\nHello $text!\nHello $text!",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            fontFamily = FontFamily.SansSerif,
            letterSpacing = 2.sp,
            maxLines = 2,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.Center
        )
    }

}

@Preview(showBackground = true)
@Composable
fun TextPreview() {
    ComposeStudyTheme {
        TextScreen()
    }
}
