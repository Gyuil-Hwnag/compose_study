package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
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

@ExperimentalPagerApi
@Composable
fun QuickCardScreen() {
    val quickCards = listOf<QuickCardType>(
        QuickCardType.WELCOME,
        QuickCardType.RESERVATION,
        QuickCardType.D_DAY,
        QuickCardType.BEFORE_REVIEW,
        QuickCardType.AFTER_REVIEW,
        QuickCardType.RE_RESERVATION,
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
        QuickCardType.BEFORE_REVIEW -> BeforeReviewCard()
        QuickCardType.AFTER_REVIEW -> AfterReviewCard()
        QuickCardType.RE_RESERVATION -> ReReservationCard()
        QuickCardType.MY_DESIGNER -> {}
        QuickCardType.MY_MENU -> {}
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
        QuickCardType.RE_RESERVATION -> Text(text = "재예약하기", color = Color.Black, fontSize = 15.sp)
        QuickCardType.MY_DESIGNER -> {}
        QuickCardType.MY_MENU -> {}
        QuickCardType.PROFILE_UPDATE -> Text(text = "기본", color = Color.Black, fontSize = 15.sp)
        QuickCardType.NORMAL -> Text(text = "프로필 업데이트", color = Color.Black, fontSize = 15.sp)
    }
}

@Composable
fun WelcomeCard() {
    Button(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
        border = BorderStroke(4.dp, color = Color.Yellow),
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
    Button(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
        border = BorderStroke(4.dp, color = Color(0xFFFF695B)),
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
            Column {
                Text(text = "여성 디자인컷", fontSize = 15.sp, color = Color.White)
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = "이수 수석 ・ 준오헤어 판교점", fontSize = 13.sp, color = Color.White)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "D-5", fontSize = 15.sp, color = Color.White)
        }
    }
}

@Composable
fun DDayCard() {
    Button(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
        border = BorderStroke(4.dp, color = Color(0xFF666DC5)),
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
            Column {
                Text(text = "여성 디자인컷", fontSize = 15.sp, color = Color.White)
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = "이수 수석 ・ 준오헤어 판교점", fontSize = 13.sp, color = Color.White)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(text = "10:56:22", fontSize = 15.sp, color = Color.White)
        }
    }
}

@Composable
fun BeforeReviewCard() {
    Button(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
        border = BorderStroke(4.dp, color = Color(0xFF888888)),
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
            Column {
                Text(text = "여성 디자인컷", fontSize = 15.sp, color = Color.White)
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = "이수 수석 ・ 준오헤어 판교점", fontSize = 13.sp, color = Color.White)
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.ic_review), contentDescription = "리뷰 아이콘", modifier = Modifier.size(28.dp))
                Text(text = "리뷰 쓰기", fontSize = 11.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun AfterReviewCard() {
    Button(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
        border = BorderStroke(4.dp, color = Color(0xFF888888)),
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
            Column {
                Text(text = "여성 디자인컷", fontSize = 15.sp, color = Color.White)
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = "이수 수석 ・ 준오헤어 판교점", fontSize = 13.sp, color = Color.White)
            }
            Spacer(modifier = Modifier.weight(1f))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Image(painter = painterResource(id = R.drawable.ic_review), contentDescription = "리뷰 아이콘", modifier = Modifier.size(28.dp))
                Text(text = "리뷰 보기", fontSize = 11.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun ReReservationCard() {
    Button(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
        border = BorderStroke(4.dp, color = Color(0xFF74E0C7)),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFF74E0C7)),
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
            Column {
                Text(text = "재예약하기", fontSize = 15.sp, color = Color.White)
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = "남성 디자인컷・준오헤어 판교돔시티점" , fontSize = 13.sp, color = Color.White)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun NormalCard() {
    Button(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
        border = BorderStroke(4.dp, color = Color(0xFFDDDDDD)),
        colors = ButtonDefaults.buttonColors(backgroundColor = Color(0xFFDDDDDD)),
        onClick = {}
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(modifier = Modifier.height(56.dp))
            Column {
                Text(text = "여성 디자인컷", fontSize = 15.sp, color = Color.White)
                Spacer(modifier = Modifier.size(4.dp))
                Text(text = "이수 수석 ・ 준오헤어 판교점" , fontSize = 13.sp, color = Color.White)
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun UpdateProfileCard() {
    Button(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 4.dp),
        border = BorderStroke(4.dp, color = Color(0xFF666DC5)),
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
    WELCOME, RESERVATION, D_DAY, BEFORE_REVIEW, AFTER_REVIEW, RE_RESERVATION, MY_DESIGNER, MY_MENU, NORMAL, PROFILE_UPDATE
}

@OptIn(ExperimentalPagerApi::class)
@Preview
@Composable
fun UserCardPreview() {
    Compose_studyTheme {
        QuickCardScreen()
    }
}