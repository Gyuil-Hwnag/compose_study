package com.example.compose_study.ui.screen.stylebook

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_study.R
import com.example.compose_study.ui.screen.feature.component.clickableSingle
import com.example.compose_study.utils.VerticalDivider

@Composable
fun StyleBookHeader(
    offset: Float = 0f,
    title: String,
    onSearchClicked: () -> Unit,
    onPhotoClicked: () -> Unit
) {
    val colorAlpha = (offset / 100).coerceIn(0f..1f)
    val iconWidth = 96 * ((100f - offset) / 100).coerceIn(0.7f .. 1f)
    val iconHeight = 44 * ((100f - offset) / 100).coerceIn(0.7f .. 1f)
    Surface(
        color = Color.White.copy(alpha = colorAlpha),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(vertical = 12.dp, horizontal = 6.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Spacer(modifier = Modifier.width(iconWidth.dp).height(iconHeight.dp))
                Text(
                    text = title,
                    fontSize = 15.sp,
                    color = Color.Black.copy(alpha = colorAlpha)
                )
                IconButton(
                    modifier = Modifier.width(iconWidth.dp).height(iconHeight.dp),
                    onClick = {}
                ) {
                    Row {
                        Icon(
                            modifier = Modifier.clickableSingle { onSearchClicked() },
                            painter = painterResource(R.drawable.ic_search),
                            contentDescription = "임시 아이콘",
                        )
                        VerticalDivider(dp = 8)
                        Icon(
                            modifier = Modifier.clickableSingle { onPhotoClicked() },
                            painter = painterResource(R.drawable.ic_photo),
                            contentDescription = "임시 아이콘"
                        )
                    }
                }
            }
        }
    }
}
