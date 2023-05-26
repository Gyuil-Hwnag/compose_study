package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose_study.R
import com.example.compose_study.ui.theme.Compose_studyTheme
import com.example.compose_study.ui.theme.Shapes

@Composable
fun ReReservationScreen() {
    val tabs = listOf<Category>(
        Category("커트"),
        Category("펌"),
        Category("염색")
    )
    val menu1 = PickMenu(
        menuImg = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTrKuHJGRWc9N7aSxp8Z9b_G1Ypu2ZDI7Bl1g&usqp=CAU",
        menuName = "엑스트라 신데렐라 케라틴 클리닉",
        price = "40,000",
        sales = "29,900",
        saleRatio = "40%"
    )
    val menu2 = PickMenu(
        menuImg = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjVlZo3j6UOTSdINV6h0R6Ll5YxZochezX6h8Z7DcZ89XAaNTIN2etv3tOdpkc9e4FrxY&usqp=CAU",
        menuName = "엑스트라 신데렐라 케라틴 클리닉",
        price = "40,000",
        sales = "29,900",
        saleRatio = "40%"
    )

    val reservation = ReReservation(
        shopImg = "http://www.04one.co.kr/data/goodsImages/GOODS1_160577396320201127155905.jpg",
        shopName = "오슈아헤어 강남논현점",
        shopDescription = "역삼역 3번출구 도보 5분, 스타타워뒷길",
        shopRatio = 4.8,
        shopReviews = 21,
        pickMenus = listOf(menu1, menu2)
    )
    var selectIndex by remember { mutableStateOf(0) }

    Column(modifier = Modifier.fillMaxWidth().background(Color.White)) {
        ReReservationTitle()
        Tab(
            tabs = tabs,
            selectedTabIndex = selectIndex,
            onTabClicked = { tabIndex, _ -> selectIndex = tabIndex }
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(vertical = 18.dp),
            contentPadding = PaddingValues(end = 48.dp, start = 16.dp)
        ) {
            items(listOf(reservation, reservation, reservation)) {
                ReReservationItem(item = it)
            }
        }
    }
}

@Composable
fun ReReservationTitle() {
    Row {
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = "시술 별 재예약 많은 샵", fontSize = 20.sp)
    }
}

@Composable
fun ReReservationItem(item: ReReservation) {
    Column(modifier = Modifier.padding(6.dp)) {
        Surface(
            shape = RoundedCornerShape(4.dp)
        ) {
            AsyncImage(
                model = item.shopImg,
                contentDescription = "매장 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(221.dp),
            )
        }
        Spacer(modifier = Modifier.size(16.dp))
        Text(text = item.shopName, color = Color.Black, fontSize = 16.sp)
        Spacer(modifier = Modifier.size(4.dp))
        Text(text = item.shopDescription, color = Color(0xFF888888), fontSize = 12.sp)
        Spacer(modifier = Modifier.size(12.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painter = painterResource(id = R.drawable.ic_star), contentDescription = "평점 이미지")
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = item.shopRatio.toString(), color = Color.Black, fontSize = 13.sp)
            Spacer(modifier = Modifier.size(12.dp))
            Image(painter = painterResource(id = R.drawable.ic_review), contentDescription = "리뷰 이미지")
            Spacer(modifier = Modifier.size(4.dp))
            Text(text = item.shopReviews.toString(), color = Color.Black, fontSize = 13.sp)
        }
        Column {
            item.pickMenus.forEach {
                PickMenuItem(item = it)
            }
        }
    }
}

@Composable
fun PickMenuItem(item: PickMenu) {
    Column {
        Spacer(modifier = Modifier
            .width(299.dp)
            .background(Color(0xFFF7F7F7)))
        Spacer(modifier = Modifier.size(16.dp))
        Row {
            Surface(
                shape = CircleShape
            ) {
                AsyncImage(
                    model = item.menuImg,
                    contentDescription = "메뉴 이미지",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(48.dp)
                )
            }
            Spacer(modifier = Modifier.size(12.dp))
            Column(verticalArrangement = Arrangement.Center) {
                Text(text = item.menuName, fontSize = 13.sp, color = Color.Black)
                Spacer(modifier = Modifier.size(6.dp))
                Row {
                    Text(text = item.saleRatio, fontSize = 13.sp, color = Color.Red)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = item.sales, fontSize = 13.sp, color = Color.Black)
                    Spacer(modifier = Modifier.size(4.dp))
                    Text(text = item.price, fontSize = 13.sp, color = Color(0xFFCCCCCC), textDecoration = TextDecoration.LineThrough)
                }
            }
        }
        Spacer(modifier = Modifier.size(16.dp))
    }
}

data class ReReservation(
    val shopImg: String,
    val shopName: String,
    val shopDescription: String,
    val shopRatio: Double,
    val shopReviews: Int,
    val pickMenus: List<PickMenu>
)

data class PickMenu(
    val menuImg: String,
    val menuName: String,
    val price: String,
    val sales: String,
    val saleRatio: String
)

@Preview
@Composable
fun ReReservationPreview() {
    Compose_studyTheme {
        ReReservationScreen()
    }
}