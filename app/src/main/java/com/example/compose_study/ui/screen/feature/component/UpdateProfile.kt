package com.example.compose_study.ui.screen.feature.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compose_study.R

@Composable
fun UpdateProfileCard() {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
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