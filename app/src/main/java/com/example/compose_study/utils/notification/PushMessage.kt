package com.example.compose_study.utils.notification

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

typealias PushMessageId = String

@Parcelize
data class PushMessage(
    val id: PushMessageId,
    val type: Type,
    val typeName: String,
    val soundMode: SoundMode,
    val title: String,
    val message: String,
    val imageUrl: String,
    val uri: String
) : Parcelable {

    val hasContents: Boolean
        get() = title.isNotBlank() || message.isNotBlank() || imageUrl.isNotBlank()

    val hasImageUrl: Boolean
        get() = imageUrl.isNotBlank()

    enum class SoundMode {
        NONE, DEFAULT
    }

    enum class Type {
        MARKETING, GENERAL, UNKNOWN
    }
}

val testPushMessage: PushMessage =
    PushMessage(
        id = "test",
        type = PushMessage.Type.GENERAL,
        typeName = "GENERAL",
        soundMode = PushMessage.SoundMode.DEFAULT,
        title = "테스트 알림",
        message = "테스트 알림이 왔어요~",
        imageUrl = "",
        uri = ""
    )
