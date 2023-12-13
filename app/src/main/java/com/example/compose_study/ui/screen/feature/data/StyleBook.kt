package com.example.compose_study.ui.screen.feature.data

data class StyleBook(
    val imgUri: String,
    val title: String,
    val description: String
)

private val styleBook1 = StyleBook(
    imgUri = "https://mud-kage.kakao.com/dn/L9UdN/btsAJH5wAeB/EtTetfFHsKohELIwNsMlk1/img_750.jpg",
    title = "요즘 헤어 추구미\n일상에서도 유니크하게",
    description = "#처피뱅 #해쉬컷 #발레야쥬"
)
private val styleBook2 = StyleBook(
    imgUri = "https://mud-kage.kakao.com/dn/b7Z4VW/btsz6gGx68s/SOpIKTYerfjzdMCbg52Rvk/img_750.jpg",
    title = "요즘 헤어 추구미\n러블리 & 페미닌 무드 스타일",
    description = "#히피펌 #숏컷 #리프컷"
)
private val styleBook3 = StyleBook(
    imgUri = "https://mud-kage.kakao.com/dn/1paLT/btsyFoGNEqm/m7kIsrwvVCg7qHmKkVeLm1/img_750.jpg",
    title = "남자들의 워너비 헤어\n장발 추천 스타일 모음",
    description = "장발로 기를 때 하기 좋은 머리"
)
private val styleBook4 = StyleBook(
    imgUri = "https://mud-kage.kakao.com/dn/9iUwt/btsA20Q4Jq1/KjS7h8paKvYvBnTqFXMIkK/img_750.jpg",
    title = "비슷해 보여도 달라요!\n알고 쓰면 좋은 홈케어 사용법",
    description = "트린트먼트 vs 린스, 뭐가 다를까?"
)
private val styleBook5 = StyleBook(
    imgUri = "https://mud-kage.kakao.com/dn/b7t8zw/btsAmsOpz89/O7tu3eQUNLEkTyl0bQroIK/img_750.jpg",
    title = "나에게 딱 맞는\n가을 헤어 컬러는?",
    description = "피부 톤 찰떡 염색 컬러 추천"
)

val styleBooks = listOf(styleBook1, styleBook2, styleBook3, styleBook4, styleBook5)
