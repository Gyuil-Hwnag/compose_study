package com.example.compose_study.model

data class Puppy(
    val name: String,
    val image: String,
    val content: String
)

object DataProvider {
    val puppyList = listOf(
        Puppy("코코", "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "코코코코코 코 코코코 입!"),
        Puppy("보리", "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "보리 보리 쌀"),
        Puppy("초코", "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "초코송이 먹고싶당"),
        Puppy("콩이", "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "나는 콩을 안좋아하지만 콩이는 좋아!"),
        Puppy("사랑이", "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "사랑이 덕분에 집에 사랑이 가득~"),
        Puppy("달이", "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "달 달 무슨 달 쟁반같이 둥근 달"),
        Puppy("별이", "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "별.. 별.. 별.."),
        Puppy("해피", "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "암 쏘 해피~~~~ "),
        Puppy("마루", "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "마루야.. 가지마.."),
        Puppy("까미", "https://images.pexels.com/photos/1108099/pexels-photo-1108099.jpeg?cs=srgb&dl=pexels-chevanon-photography-1108099.jpg&fm=jpg", "난 영어 이름도 있어. Kami")
    )
}
