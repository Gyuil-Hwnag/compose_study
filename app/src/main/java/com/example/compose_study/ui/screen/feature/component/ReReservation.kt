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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose_study.R
import com.example.compose_study.ui.theme.ComposeStudyTheme

@Composable
fun ReReservationScreen(
    tabs: List<Category>,
    selectedTab: Int,
    onTabClicked: (Int) -> Unit
) {
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

    Column(
        modifier = Modifier.fillMaxWidth().background(Color.White)
    ) {
        ReReservationTitle()
        BottomIndicatorTabRow(
            tabs = tabs,
            selectedTabIndex = selectedTab,
            onTabClicked = { tabIndex, _ -> onTabClicked(tabIndex) }
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth().padding(vertical = 18.dp),
            contentPadding = PaddingValues(start = 10.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(listOf(reservation, reservation, reservation)) {
                ReReservationItem(item = it)
            }
        }
        ContentsDivider()
    }
}

@Composable
fun ReReservationTitle() {
    Text(modifier = Modifier.padding(horizontal = 16.dp), text = "시술 별 재예약 많은 샵", fontSize = 20.sp)
}

@Composable
fun ReReservationItem(item: ReReservation) {
    Column {
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
        Text(
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = item.shopName,
            color = Color.Black,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.size(4.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            text = item.shopDescription,
            color = Color(0xFF888888),
            fontSize = 12.sp
        )
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
        Row(
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
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
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    text = item.menuName,
                    fontSize = 13.sp, color = Color.Black
                )
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
    ComposeStudyTheme {
        ReReservationScreen(emptyList(), 0, { Unit })
    }
}
