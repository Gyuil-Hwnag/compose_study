package com.example.compose_study.utils.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun VerticalDivider(dp: Int) {
    Spacer(
        modifier = Modifier
            .height(dp.dp)
            .wrapContentWidth()
    )
}

@Composable
fun HorizontalDivider(dp: Int) {
    Spacer(
        modifier = Modifier
            .width(dp.dp)
            .wrapContentHeight()
    )
}

@Composable
fun ColorLineDivider(
    color: Color = Color(0xFFF3F3F3)
) {
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(1.dp)
            .background(color)
    )
}

@Composable
fun RowScope.WeightDivider(weight: Float) {
    Spacer(modifier = Modifier.weight(weight))
}

@Composable
fun ColumnScope.WeightDivider(weight: Float) {
    Spacer(modifier = Modifier.weight(weight))
}

@Composable
fun MainBottomNavigationDivider(color: Color = Color.White) {
    Spacer(modifier = Modifier.fillMaxWidth().height(56.dp).background(color))
}