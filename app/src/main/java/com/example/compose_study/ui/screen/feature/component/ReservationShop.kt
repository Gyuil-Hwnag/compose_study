package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.compose_study.ui.theme.Compose_studyTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ReservationShopScreen() {
    val tabs = listOf<Category>(
        Category("헤어샵"),
        Category("네일샵"),
        Category("바버샵"),
        Category("에스테틱")
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

    val reservations = listOf(reservation, reservation, reservation)

    val state = rememberPagerState()
    var selectIndex by remember { mutableStateOf(0) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        ReservationTitle()
        Tab(
            tabs = tabs,
            selectedTabIndex = selectIndex,
            onTabClicked = { tabIndex, _ -> selectIndex = tabIndex }
        )

        HorizontalPager(
            state = state,
            count = reservations.size,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(end = 12.dp, start = 12.dp),
            itemSpacing = 6.dp
        ) { page ->
            ShopItem(item = reservations[page])
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .size(24.dp)
        )
        Indicator(
            totalDots = reservations.size,
            selectedIndex = state.currentPage
        )
        ContentsDivider()
    }
}

@Composable
fun ReservationTitle() {
    Text(modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp), text = "우리 동네 예약 가능한 매장", fontSize = 20.sp)
}

@Composable
fun ShopItem(item: ReReservation) {
    Column(
        modifier = Modifier
            .width(331.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(4.dp)
        ) {
            AsyncImage(
                model = item.shopImg,
                contentDescription = "매장 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .aspectRatio(1.5f),
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

@Preview
@Composable
fun ReservationShopPreview() {
    Compose_studyTheme {
        ReservationShopScreen()
    }
}