package com.example.compose_study.chap1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.example.compose_study.R
import com.example.compose_study.ui.theme.Compose_studyTheme


@Composable
fun ImageScreen() {
    ImageExample()
}

@Composable
fun ImageExample() {
    Column(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = "")
        Image(imageVector = Icons.Filled.Settings, contentDescription = "")

        /** Context가 있는 Activity에서 가져와서 사용해야 함 */
//        Image(bitmap = , contentDescription = "")

        val painter = rememberImagePainter(data = "https://cdn.travie.com/news/photo/first/201710/img_19975_1.jpg")
        Image(painter = painter, contentDescription = "")
        AsyncImage(model = "https://mblogthumb-phinf.pstatic.net/MjAxODA2MjZfMTMw/MDAxNTMwMDE0NDk2Mzcz.7re42MA5wqJxZlJ8J5FzfDKEEqugtVuhg49bSFYUuYsg.0Y0kjwH4oi1LXXpqrcGaVBch_4eQsyKyVTRsNtg7fCMg.JPEG.ichufs/%EC%82%AC%EC%A7%84%EC%8C%A4%EC%9A%B0%EC%93%B0%EB%9D%BC_3_0%EC%9D%B8%ED%8A%B8%EB%A1%9C.jpg?type=w800", contentDescription = "")

    }
}

@Preview(showBackground = true)
@Composable
fun ImagePreview() {
    Compose_studyTheme {
        ImageScreen()
    }
}
