package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose_study.R
import com.example.compose_study.ui.theme.ComposeStudyTheme

@Composable
fun RecentStyleScreen() {
    val style1 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrKuHJGRWc9N7aSxp8Z9b_G1Ypu2ZDI7Bl1g&usqp=CAU"
    val style2 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjVlZo3j6UOTSdINV6h0R6Ll5YxZochezX6h8Z7DcZ89XAaNTIN2etv3tOdpkc9e4FrxY&usqp=CAU"
    val style3 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRqwuLtzNxwEa6dsXWzSa0p7RaixhleZ7jP9w&usqp=CAU"
    val style4 = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSg25GD2Ly8O-j0zCsX6qgXo4aeAlMyPqhivA&usqp=CAU"
    val styles = listOf(style1, style2, style3, style4)

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        RecentStyleTitle()
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(11.dp))
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(start = 10.dp)
        ) {
            items(styles) {
                RecentStyleItem(item = it)
            }
        }
        ContentsDivider()
    }
}

@Composable
fun RecentStyleTitle() {
    Row(modifier = Modifier.padding(horizontal = 16.dp), verticalAlignment = Alignment.CenterVertically) {
        Text(text = "최근 본 스타일", fontSize = 20.sp)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = "전체삭제", fontSize = 13.sp, color = Color(0xFFAAAAAA))
    }
}

@Composable
fun RecentStyleItem(item: String) {
    Box(
        modifier = Modifier.padding(5.dp),
        contentAlignment = Alignment.TopEnd,
    ) {
        Surface(
            shape = RoundedCornerShape(4.dp)
        ) {
            AsyncImage(
                modifier = Modifier.size(120.dp),
                contentScale = ContentScale.Crop,
                model = item,
                contentDescription = "스타일 이미지"
            )
        }

        Image(
            modifier = Modifier.padding(4.dp),
            painter = painterResource(id = R.drawable.ic_close),
            contentDescription = "삭제 이미지"
        )
    }
}

@Preview
@Composable
fun RecentStylePreview() {
    ComposeStudyTheme {
        RecentStyleScreen()
    }
}
