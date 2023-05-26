package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose_study.R
import com.example.compose_study.ui.theme.Compose_studyTheme

@Composable
fun RecommendStyleScreen() {
    val tabs = listOf<Category>(
        Category("요즘 떠오르는"),
        Category("유저가 추천하는"),
        Category("디자이너가 추천하는"),
        Category("찜 많은"),
        Category("시술 횟수가 가장많은")
    )

    val style1 = RecommendStyle(
        imgUri = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrKuHJGRWc9N7aSxp8Z9b_G1Ypu2ZDI7Bl1g&usqp=CAU",
        tag = listOf("#C컬펌", "#레이어드컷")
    )
    val style2 = RecommendStyle(
        imgUri = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjVlZo3j6UOTSdINV6h0R6Ll5YxZochezX6h8Z7DcZ89XAaNTIN2etv3tOdpkc9e4FrxY&usqp=CAU",
        tag = listOf("#C컬펌")
    )
    val style3 = RecommendStyle(
        imgUri = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRqwuLtzNxwEa6dsXWzSa0p7RaixhleZ7jP9w&usqp=CAU",
        tag = listOf("#C컬펌")
    )
    val style4 = RecommendStyle(
        imgUri = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSg25GD2Ly8O-j0zCsX6qgXo4aeAlMyPqhivA&usqp=CAU",
        tag = listOf("#C컬펌", "#레이어드컷")
    )

    var selectIndex by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        RecommendStyleTitle()
        Tab(
            tabs = tabs,
            selectedTabIndex = selectIndex,
            onTabClicked = { tabIndex, _ -> selectIndex = tabIndex }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    modifier = Modifier.weight(1f)
                ) {
                    RecommendStyleItem(item = style1)
                }
                Spacer(modifier = Modifier.size(12.dp))
                Surface(
                    modifier = Modifier.weight(1f)
                ) {
                    RecommendStyleItem(item = style2)
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Surface(
                    modifier = Modifier.weight(1f)
                ) {
                    RecommendStyleItem(item = style3)
                }
                Spacer(modifier = Modifier.size(12.dp))
                Surface(
                    modifier = Modifier.weight(1f)
                ) {
                    RecommendStyleItem(item = style4)
                }
            }
        }
        RecommendStyleMore()
    }
}

@Composable
fun RecommendStyleTitle() {
    Row {
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "시에나님을 위한 스타일 추천", fontSize = 20.sp)
    }
}

@Composable
fun RecommendStyleItem(item: RecommendStyle) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 12.dp)) {
        Surface(
            shape = RoundedCornerShape(4.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(0.84f),
                model = item.imgUri,
                contentDescription = "메뉴 이미지",
                contentScale = ContentScale.Crop,
            )
        }
        Row(modifier = Modifier.padding(vertical = 6.dp)) {
            item.tag.forEach {
                StyleTag(item = it)
            }
        }
    }
}

@Composable
fun StyleTag(item: String) {
    Row {
        Surface(
            shape = RoundedCornerShape(2.dp),
            color = Color(0xFFF3F3F3)
        ) {
            Text(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                text = item,
                fontSize = 12.sp,
                color = Color.Black
            )
        }
        Spacer(modifier = Modifier.size(4.dp))
    }
}

@Composable
fun RecommendStyleMore() {
    Row(modifier = Modifier.padding(horizontal = 16.dp)) {
        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 14.dp),
            onClick = {},
            border = BorderStroke(1.dp, Color(0xFFEEEEEE)),
            shape = RoundedCornerShape(4.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            elevation = ButtonDefaults.elevation(0.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    text = "스타일 모두보기",
                    fontSize = 13.sp,
                    color = Color(0xFF555555),
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.size(4.dp))
                Icon(painter = painterResource(id = R.drawable.ic_arrow_right), contentDescription = "모두 보기 버튼")
            }

        }
    }
}

data class RecommendStyle(
    val imgUri: String,
    val tag: List<String>
)

@Preview
@Composable
fun RecommendStylePreview() {
    Compose_studyTheme {
        RecommendStyleScreen()
    }
}
