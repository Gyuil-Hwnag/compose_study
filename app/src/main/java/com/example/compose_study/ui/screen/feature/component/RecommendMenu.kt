package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose_study.R
import com.example.compose_study.ui.theme.ComposeStudyTheme

@Composable
fun RecommendMenuScreen(
    onMenuClicked: () -> Unit
) {

    val tabs = listOf<Category>(
        Category("테슬컷"),
        Category("히피펌"),
        Category("레이어드컷"),
        Category("발리아쥬"),
        Category("머쉬룸컷")
    )

    val menu1 = Menu(
        imgUri = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrKuHJGRWc9N7aSxp8Z9b_G1Ypu2ZDI7Bl1g&usqp=CAU",
        shopName = "밈 청담본점",
        menuName = "매직셋팅 S컬 + 클리닉",
        price = "187,000원",
        saleRatio = "15%"
    )
    val menu2 = Menu(
        imgUri = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjVlZo3j6UOTSdINV6h0R6Ll5YxZochezX6h8Z7DcZ89XAaNTIN2etv3tOdpkc9e4FrxY&usqp=CAU",
        shopName = "밈 청담본점",
        menuName = "매직셋팅 S컬 + 클리닉",
        price = "187,000원",
        saleRatio = "15%"
    )
    val menu3 = Menu(
        imgUri = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRqwuLtzNxwEa6dsXWzSa0p7RaixhleZ7jP9w&usqp=CAU",
        shopName = "밈 청담본점",
        menuName = "매직셋팅 S컬 + 클리닉",
        price = "187,000원",
        saleRatio = "15%"
    )
    val menu4 = Menu(
        imgUri = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSg25GD2Ly8O-j0zCsX6qgXo4aeAlMyPqhivA&usqp=CAU",
        shopName = "밈 청담본점",
        menuName = "매직셋팅 S컬 + 클리닉",
        price = "187,000원",
        saleRatio = "15%"
    )

    val menus = listOf<Menu>(menu1, menu2, menu3, menu4, menu1, menu2, menu3, menu4)

    var selectIndex by remember { mutableStateOf(0) }
    Column(
        modifier = Modifier.fillMaxWidth().background(Color.White).clickable{ onMenuClicked() }
    ) {
        RecommendMenuTitle()
        BottomIndicatorTabRow(
            tabs = tabs,
            selectedTabIndex = selectIndex,
            onTabClicked = { tabIndex, _ -> selectIndex = tabIndex }
        )
        LazyRow(
            modifier = Modifier.padding(vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(menus) {
                MenuItem(menu = it)
            }
        }
        ContentsDivider()
    }
}

@Composable
fun RecommendMenuTitle() {
    Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), verticalAlignment = Alignment.Bottom) {
        Text(text = "시에나님을 위한 추천 메뉴", fontSize = 20.sp)
        Image(painter = painterResource(id = R.drawable.ic_info), contentDescription = "")
    }
}

@Composable
fun MenuItem(menu: Menu) {
    Column {
        Surface(
            shape = RoundedCornerShape(4.dp)
        ) {
            AsyncImage(
                model = menu.imgUri,
                contentDescription = "메뉴 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(160.dp),
            )
        }
        Spacer(modifier = Modifier.size(12.dp))
        Text(text = menu.shopName, color = Color(0xFF888888), fontSize = 13.sp)
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = menu.menuName, color = Color.Black, fontSize = 14.sp)
        Spacer(modifier = Modifier.size(4.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(text = menu.saleRatio, color = Color.Red, fontSize = 13.sp)
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = menu.price, color = Color.Black, fontSize = 13.sp)
        }
    }
}

data class Menu(
    val imgUri: String,
    val shopName: String,
    val menuName: String,
    val price: String,
    val saleRatio: String
)


@Preview
@Composable
fun RecommendMenuPreview() {
    ComposeStudyTheme {
        RecommendMenuScreen({})
    }
}
