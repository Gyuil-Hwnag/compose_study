package com.example.compose_study.utils.notification

import kotlin.random.Random

internal fun getPushMessage(): PushMessage {
    val random = Random.nextInt()
    return when (random % 6) {
        0 -> testPushMessage
        1 -> testPushMessageWithImage
        2 -> testPushMessageOnlyImage
        3 -> testPushMessageSoundNone
        4 -> testPushMessageWithImageSoundNone
        5 -> testPushMessageOnlyImageSoundNone
        else -> testPushMessage
    }
}

internal fun getExactPushMessage(): PushMessage {
    val random = Random.nextInt()
    return when (random % 3) {
        0 -> testPushMessage
        1 -> testPushMessageWithImage
        2 -> testPushMessageOnlyImage
        else -> testPushMessage
    }
}

internal fun getNonExactPushMessage(): PushMessage {
    val random = Random.nextInt()
    return when (random % 3) {
        0 -> testPushMessageSoundNone
        1 -> testPushMessageWithImageSoundNone
        2 -> testPushMessageOnlyImageSoundNone
        else -> testPushMessageSoundNone
    }
}

private val testPushMessage: PushMessage =
    PushMessage(
        id = "test",
        type = PushMessage.Type.GENERAL,
        typeName = "GENERAL",
        soundMode = PushMessage.SoundMode.DEFAULT,
        timeMode = PushMessage.TimeMode.EXACT,
        title = "문구 테스트 알림 (Sound Default)",
        message = "테스트 알림이 왔어요~\n기본 테스트 알림이 왔어요~",
        imageUrl = "",
        uri = ""
    )

private val testPushMessageWithImage: PushMessage =
    PushMessage(
        id = "test",
        type = PushMessage.Type.GENERAL,
        typeName = "GENERAL",
        soundMode = PushMessage.SoundMode.DEFAULT,
        timeMode = PushMessage.TimeMode.EXACT,
        title = "이미지 문구 테스트 알림 (Sound Default)",
        message = "테스트 알림이 왔어요~\n강아지 사진이 있는 테스트 알림이 왔어요~",
        imageUrl = "https://png.pngtree.com/thumb_back/fh260/background/20230609/pngtree-three-puppies-with-their-mouths-open-are-posing-for-a-photo-image_2902292.jpg",
        uri = ""
    )

private val testPushMessageOnlyImage: PushMessage =
    PushMessage(
        id = "test",
        type = PushMessage.Type.GENERAL,
        typeName = "GENERAL",
        soundMode = PushMessage.SoundMode.DEFAULT,
        timeMode = PushMessage.TimeMode.EXACT,
        title = "이미지 만 있는 테스트 알림 (Sound Default)",
        message = "",
        imageUrl = "https://png.pngtree.com/thumb_back/fh260/background/20230609/pngtree-three-puppies-with-their-mouths-open-are-posing-for-a-photo-image_2902292.jpg",
        uri = ""
    )

private val testPushMessageSoundNone: PushMessage =
    PushMessage(
        id = "test",
        type = PushMessage.Type.GENERAL,
        typeName = "GENERAL",
        soundMode = PushMessage.SoundMode.NONE,
        timeMode = PushMessage.TimeMode.NON_EXACT,
        title = "문구 테스트 알림 (Sound None)",
        message = "테스트 알림이 왔어요~\n기본 테스트 알림이 왔어요~",
        imageUrl = "",
        uri = ""
    )

private val testPushMessageWithImageSoundNone: PushMessage =
    PushMessage(
        id = "test",
        type = PushMessage.Type.GENERAL,
        typeName = "GENERAL",
        soundMode = PushMessage.SoundMode.NONE,
        timeMode = PushMessage.TimeMode.NON_EXACT,
        title = "이미지 문구 테스트 알림 (Sound None)",
        message = "테스트 알림이 왔어요~\n강아지 사진이 있는 테스트 알림이 왔어요~",
        imageUrl = "https://png.pngtree.com/thumb_back/fh260/background/20230609/pngtree-three-puppies-with-their-mouths-open-are-posing-for-a-photo-image_2902292.jpg",
        uri = ""
    )

private val testPushMessageOnlyImageSoundNone: PushMessage =
    PushMessage(
        id = "test",
        type = PushMessage.Type.GENERAL,
        typeName = "GENERAL",
        soundMode = PushMessage.SoundMode.NONE,
        timeMode = PushMessage.TimeMode.NON_EXACT,
        title = "이미지 만 있는 테스트 알림 (Sound None)",
        message = "",
        imageUrl = "https://png.pngtree.com/thumb_back/fh260/background/20230609/pngtree-three-puppies-with-their-mouths-open-are-posing-for-a-photo-image_2902292.jpg",
        uri = ""
    )
