package com.example.compose_study.utils.notification

import android.annotation.TargetApi
import android.os.Build
import com.example.compose_study.R
import com.example.compose_study.utils.toEnumValueOfOrNull
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class FirebaseMessagingService : FirebaseMessagingService() {

    private val serviceJob = Job()
    private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        val data = remoteMessage.data
        if(data.isEmpty() || data.containsKey("af-uinstall-tracking")) return

        val pushMessage = data.toPushMessage()
        if (pushMessage.hasContents) {
            sendNotification(pushMessage)
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    fun sendNotification(message: PushMessage) = serviceScope.launch{
        NotificationHelper.notifyPushMessage(applicationContext, message)
    }

    override fun onDestroy() {
        serviceJob.cancel()
        super.onDestroy()
    }

    private fun Map<String, String>.toPushMessage(): PushMessage {
        val response = this
        return PushMessage(
            id = response["pushId"] ?: "",
            type = response["type"]?.uppercase(Locale.US)?.toEnumValueOfOrNull<PushMessage.Type>() ?: PushMessage.Type.UNKNOWN,
            typeName = response["typeName"] ?: "",
            soundMode = response["sound"]?.uppercase(Locale.US)?.toEnumValueOfOrNull<PushMessage.SoundMode>() ?: PushMessage.SoundMode.NONE,
            title = response["title"] ?: getString(R.string.app_name),
            message = response["content"] ?: "",
            imageUrl = response["imageUrl"] ?: "",
            uri = response["link"] ?: ""
        )
    }
}
