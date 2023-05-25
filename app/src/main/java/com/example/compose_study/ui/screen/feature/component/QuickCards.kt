package com.example.compose_study.ui.screen.feature.component

import androidx.compose.animation.core.CubicBezierEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.compose_study.R
import com.example.compose_study.ui.theme.Compose_studyTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@ExperimentalPagerApi
@Composable
fun QuickCardScreen() {
    val quickCards = listOf<QuickCardType>(
        QuickCardType.WELCOME,
        QuickCardType.RESERVATION,
        QuickCardType.D_DAY,
        QuickCardType.BEFORE_REVIEW,
        QuickCardType.AFTER_REVIEW,
        QuickCardType.RE_RESERVATION_FEMALE,
        QuickCardType.RE_RESERVATION_MALE,
        QuickCardType.MY_DESIGNER,
        QuickCardType.MY_MENU,
        QuickCardType.NORMAL,
        QuickCardType.PROFILE_UPDATE
    )

    val state = rememberPagerState()
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            CardMessage(quickCards[state.currentPage])
        }
        Spacer(modifier = Modifier.size(16.dp))
        HorizontalPager(
            state = state,
            count = quickCards.size,
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(end = 16.dp, start = 12.dp)
        ) { page ->
            QuickCard(type = quickCards[page])
        }
    }

}

/**
 * Text Auto Slider 참고 링크 : https://stackoverflow.com/questions/68974245/marquee-text-effect-in-jetpack-compose
 **/

@Composable
fun QuickCard(type: QuickCardType) {
    when(type) {
        QuickCardType.WELCOME -> WelcomeCard()
        QuickCardType.RESERVATION -> ReservationCard()
        QuickCardType.D_DAY -> DDayCard()
        QuickCardType.BEFORE_REVIEW -> ReviewCard(isWrite = false)
        QuickCardType.AFTER_REVIEW -> ReviewCard(isWrite = true)
        QuickCardType.RE_RESERVATION_FEMALE -> ReReservationCard(isFemale = true)
        QuickCardType.RE_RESERVATION_MALE -> ReReservationCard(isFemale = false)
        QuickCardType.MY_DESIGNER -> FavoriteCard(FavoriteType.DESIGNER)
        QuickCardType.MY_MENU -> FavoriteCard(FavoriteType.MENU)
        QuickCardType.PROFILE_UPDATE -> UpdateProfileCard()
        QuickCardType.NORMAL -> NormalCard()
    }
}

@Composable
fun CardMessage(type: QuickCardType) {
    when(type) {
        QuickCardType.WELCOME -> Text(text = "신규고객", color = Color.Black, fontSize = 15.sp)
        QuickCardType.RESERVATION -> Text(text = "-N ~ 1일전", color = Color.Black, fontSize = 15.sp)
        QuickCardType.D_DAY -> Text(text = "24시간 전", color = Color.Black, fontSize = 15.sp)
        QuickCardType.BEFORE_REVIEW -> Text(text = "시술 후 - 리뷰작성 전", color = Color.Black, fontSize = 15.sp)
        QuickCardType.AFTER_REVIEW -> Text(text = "시술 후 - 리뷰작성 후", color = Color.Black, fontSize = 15.sp)
        QuickCardType.RE_RESERVATION_FEMALE -> Text(text = "여성 재예약하기", color = Color.Black, fontSize = 15.sp)
        QuickCardType.RE_RESERVATION_MALE -> Text(text = "남성 재예약하기", color = Color.Black, fontSize = 15.sp)
        QuickCardType.MY_DESIGNER -> Text(text = "나의 단골 디자이너", color = Color.Black, fontSize = 15.sp)
        QuickCardType.MY_MENU -> Text(text = "최근 본 메뉴", color = Color.Black, fontSize = 15.sp)
        QuickCardType.PROFILE_UPDATE -> Text(text = "기본", color = Color.Black, fontSize = 15.sp)
        QuickCardType.NORMAL -> Text(text = "프로필 업데이트", color = Color.Black, fontSize = 15.sp)
    }
}

@Composable
fun WelcomeCard() {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow),
        onClick = {}
    ) {
        Row(
            modifier = Modifier.padding(vertical = 12.dp)
        ) {
            Text(text = "환영합니다! 웰컴쿠폰 받으세요", fontSize = 15.sp)
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "10,000원", fontSize = 15.sp)
        }
    }
}

@Composable
fun ReservationCard() {
    val scrollState = rememberScrollState()
    var animateState by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = animateState){
        scrollState.animateScrollTo(
            scrollState.maxValue,
            animationSpec = tween(1000, 200, easing = CubicBezierEasing(0f,0f,0f,0f))
        )
        scrollState.scrollTo(0)
        animateState = !animateState
    }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFFF695B)),
        onClick = {}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = LocalContext.current.getDrawable(R.mipmap.img_profile_card_foreground),
                contentDescription = "프로필 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )
            Spacer(modifier = Modifier.size(12.dp))
            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {
                Text(text = "여성 디자인컷", fontSize = 15.sp, color = Color.White)
                Text(text = "이수 수석 ・ 준오헤어 판교점", fontSize = 13.sp, color = Color.White, modifier = Modifier.horizontalScroll(scrollState, false), maxLines = 1)
            }
            Text(text = "D-5", fontSize = 15.sp, color = Color.White, modifier = Modifier.width(73.dp), textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun DDayCard() {
    val scrollState = rememberScrollState()
    var animateState by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = animateState){
        scrollState.animateScrollTo(
            scrollState.maxValue,
            animationSpec = tween(1000, 200, easing = CubicBezierEasing(0f,0f,0f,0f))
        )
        scrollState.scrollTo(0)
        animateState = !animateState
    }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF666DC5)),
        onClick = {}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = LocalContext.current.getDrawable(R.mipmap.img_profile_card_foreground),
                contentDescription = "프로필 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )
            Spacer(modifier = Modifier.size(12.dp))
            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {
                Text(text = "여성 디자인컷", fontSize = 15.sp, color = Color.White)
                Text(text = "이수 수석 ・ 준오헤어 판교점", fontSize = 13.sp, color = Color.White, modifier = Modifier.horizontalScroll(scrollState, false), maxLines = 1)
            }
            Text(text = "10:56:22", fontSize = 15.sp, color = Color.White, modifier = Modifier.width(73.dp), textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun ReviewCard(isWrite: Boolean) {
    val scrollState = rememberScrollState()
    var animateState by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = animateState){
        scrollState.animateScrollTo(
            scrollState.maxValue,
            animationSpec = tween(1000, 200, easing = CubicBezierEasing(0f,0f,0f,0f))
        )
        scrollState.scrollTo(0)
        animateState = !animateState
    }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF888888)),
        onClick = {}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = LocalContext.current.getDrawable(R.mipmap.img_profile_card_foreground),
                contentDescription = "프로필 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )
            Spacer(modifier = Modifier.size(12.dp))
            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {
                Text(text = "여성 디자인컷", fontSize = 15.sp, color = Color.White)
                Text(text = "이수 수석 ・ 준오헤어 판교점", fontSize = 13.sp, color = Color.White, modifier = Modifier.horizontalScroll(scrollState, false), maxLines = 1)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.width(73.dp)) {
                Image(painter = painterResource(id = R.drawable.ic_review), contentDescription = "리뷰 아이콘", modifier = Modifier.size(28.dp))
                Text(text = if (isWrite) "리뷰 보기" else "리뷰 쓰기" , fontSize = 11.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun ReReservationCard(isFemale: Boolean) {
    val scrollState = rememberScrollState()
    var animateState by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = animateState){
        scrollState.animateScrollTo(
            scrollState.maxValue,
            animationSpec = tween(1000, 200, easing = CubicBezierEasing(0f,0f,0f,0f))
        )
        scrollState.scrollTo(0)
        animateState = !animateState
    }

    val backgroundColor = if (isFemale) 0xFFFFA05B else 0xFF74E0C7
    val message = if (isFemale) "줄리님 스타일 손볼 때 되셨네요!" else "랄프님 머리할 때 되셨네요!"

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(backgroundColor)),
        onClick = {}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = LocalContext.current.getDrawable(R.mipmap.img_profile_card_foreground),
                contentDescription = "프로필 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )
            Spacer(modifier = Modifier.size(12.dp))
            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {
                Text(text = message, fontSize = 15.sp, color = Color.Black)
                Text(text = "이수 수석 ・ 준오헤어 판교점", fontSize = 13.sp, color = Color.Black, modifier = Modifier.horizontalScroll(scrollState, false), maxLines = 1)
            }
        }
    }
}

@Composable
fun FavoriteCard(type: FavoriteType) {
    val scrollState = rememberScrollState()
    var animateState by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = animateState){
        scrollState.animateScrollTo(
            scrollState.maxValue,
            animationSpec = tween(1000, 200, easing = CubicBezierEasing(0f,0f,0f,0f))
        )
        scrollState.scrollTo(0)
        animateState = !animateState
    }

    val backgroundColor = when(type) {
        FavoriteType.DESIGNER -> 0xFFEA6062
        FavoriteType.MENU -> 0xFF4FABC9
    }

    val message = when(type) {
        FavoriteType.DESIGNER -> "나의 단골 디자이너"
        FavoriteType.MENU -> "여성 디자인컷"
    }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(backgroundColor)),
        onClick = {}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = LocalContext.current.getDrawable(R.mipmap.img_profile_card_foreground),
                contentDescription = "프로필 이미지",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(56.dp)
            )
            Spacer(modifier = Modifier.size(12.dp))
            Column(modifier = Modifier.weight(1f), horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {
                Text(text = message, fontSize = 15.sp, color = Color.White)
                Text(text = "이수 수석 ・ 준오헤어 판교점", fontSize = 13.sp, color = Color.White, modifier = Modifier.horizontalScroll(scrollState, false), maxLines = 1)
            }
            Image(painter = painterResource(id = R.drawable.ic_close), contentDescription = "리뷰 아이콘", modifier = Modifier.size(20.dp))
        }
    }
}

@Composable
fun NormalCard() {
    val scrollState = rememberScrollState()
    var animateState by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = animateState){
        scrollState.animateScrollTo(
            scrollState.maxValue,
            animationSpec = tween(1000, 200, easing = CubicBezierEasing(0f,0f,0f,0f))
        )
        scrollState.scrollTo(0)
        animateState = !animateState
    }

    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFDDDDDD)),
        onClick = {}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.height(56.dp))
            Column(horizontalAlignment = Alignment.Start, verticalArrangement = Arrangement.Center) {
                Text(text = "여성 디자인컷", fontSize = 15.sp, color = Color.White)
                Text(text = "이수 수석 ・ 준오헤어 판교점", fontSize = 13.sp, color = Color.White, modifier = Modifier.horizontalScroll(scrollState, false), maxLines = 1)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun UpdateProfileCard() {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 4.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF666DC5)),
        onClick = {}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.height(56.dp))
            Image(painter = painterResource(id = R.mipmap.img_default_profile_card_foreground), contentDescription = "리뷰 아이콘", modifier = Modifier.size(48.dp))
            Spacer(modifier = Modifier.size(12.dp))
            Text(text = "프로필 업데이트 하고\n스타일 추천 받기!", fontSize = 15.sp, color = Color.White)
            Spacer(modifier = Modifier.weight(1f))
            Image(painter = painterResource(id = R.drawable.ic_arrow_right), contentDescription = "다음으로 가기", modifier = Modifier.size(20.dp))
        }
    }
}


enum class QuickCardType {
    WELCOME, RESERVATION, D_DAY, BEFORE_REVIEW, AFTER_REVIEW,
    RE_RESERVATION_FEMALE, RE_RESERVATION_MALE, MY_DESIGNER, MY_MENU,
    NORMAL, PROFILE_UPDATE
}

enum class FavoriteType {
    DESIGNER, MENU
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun UserCardPreview() {
    Compose_studyTheme {
        QuickCardScreen()
    }
}