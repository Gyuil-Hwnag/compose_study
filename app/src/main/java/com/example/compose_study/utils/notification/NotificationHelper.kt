@file:Suppress("DEPRECATION")

package com.example.compose_study.utils.notification

import android.annotation.TargetApi
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.Drawable
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.compose_study.R
import com.example.compose_study.ui.MainActivity
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.coroutines.resume

object NotificationHelper {

    suspend fun notifyPushMessage(context: Context, message: PushMessage) {
        val bitmap = try {
            if (message.hasImageUrl) getBitmap(context, message.imageUrl) else null
        } catch (e: Exception) {
            null
        }
        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationBuilder = createNotificationBuilder(
            context = context,
            manager = notificationManager,
            message = message,
            image = bitmap
        )
        notificationManager.notify(message.hashCode(), notificationBuilder.build())
    }

    private fun createNotificationBuilder(
        context: Context,
        manager: NotificationManager,
        message: PushMessage,
        image: Bitmap? = null
    ): NotificationCompat.Builder {
        val compositeIntent = MainActivity.starterIntent(context = context, pushMessage = message)
        val pendingIntent: PendingIntent =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                PendingIntent.getActivity(context, message.hashCode() + 1, compositeIntent, PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)
            } else {
                PendingIntent.getActivity(context, message.hashCode() + 1, compositeIntent, PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)
            }
        val notificationBuilder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = createNotificationChannel(context, message)
            manager.createNotificationChannel(channel)
            NotificationCompat.Builder(context, channel.id)
        } else {
            NotificationCompat.Builder(context)
        }

        val drawable = ResourcesCompat.getDrawable(context.resources, R.mipmap.ic_launcher, null)
        var largeIconBitmap: Bitmap? = null
        if (image != null) {
            largeIconBitmap = image
        } else {
            drawable?.let {
                largeIconBitmap = Bitmap.createBitmap(it.intrinsicWidth, it.intrinsicHeight, Bitmap.Config.ARGB_8888)
                if (largeIconBitmap != null) {
                    val canvas = Canvas(largeIconBitmap!!)
                    it.setBounds(0, 0, canvas.width, canvas.height)
                    it.draw(canvas)
                }
            }
        }

        notificationBuilder.apply {
            setSmallIcon(R.drawable.ic_notification)
            setContentTitle(message.title)
            setContentText(message.message)
            setAutoCancel(true)
            setTicker(message.message)
            setContentIntent(pendingIntent)
            largeIconBitmap?.let { setLargeIcon(it) }
            image?.let { setStyle(NotificationCompat.BigPictureStyle().bigPicture(it)) }
        }

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O && message.soundMode == PushMessage.SoundMode.DEFAULT) {
            notificationBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
            notificationBuilder.setDefaults(Notification.DEFAULT_ALL)
        }

        return notificationBuilder
    }

    private fun createNotificationChannel(
        context: Context,
        message: PushMessage
    ): NotificationChannel {
        return when (message.soundMode) {
            PushMessage.SoundMode.NONE -> createDefaultNotificationChannel(context)
            PushMessage.SoundMode.DEFAULT -> createSoundNotificationChannel(context)
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun createDefaultNotificationChannel(context: Context): NotificationChannel {
        val notificationChannel = NotificationChannel(
            context.getString(R.string.normal_notification_id),
            context.getString(R.string.normal_notification_name),
            NotificationManager.IMPORTANCE_LOW
        )

        notificationChannel.apply {
            description = context.getString(R.string.normal_notification_description)
            enableLights(true)
            lightColor = ContextCompat.getColor(context, R.color.purple_200)
            enableVibration(false)
            lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        }

        return notificationChannel
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun createSoundNotificationChannel(context: Context): NotificationChannel {
        val notificationChannel = NotificationChannel(
            context.getString(R.string.sound_notification_id),
            context.getString(R.string.sound_notification_name),
            NotificationManager.IMPORTANCE_HIGH
        )

        notificationChannel.apply {
            description = context.getString(R.string.sound_notification_description)
            enableLights(true)
            lightColor = ContextCompat.getColor(context, R.color.purple_200)
            enableVibration(true)
            lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        }

        return notificationChannel
    }

    private suspend fun getBitmap(context: Context, imageUrl: String): Bitmap? {
        var customTarget: CustomTarget<Bitmap>? = null
        val result = withTimeoutOrNull(60 * 1000) {
            awaitOnResourceReady {
                customTarget = it
                Glide.with(context)
                    .asBitmap()
                    .load(imageUrl)
                    .into(it)
            }
        }
        customTarget?.let { Glide.with(context).clear(it) }
        return result
    }

    private suspend fun awaitOnResourceReady(block: (CustomTarget<Bitmap>) -> Unit): Bitmap? =
        suspendCancellableCoroutine { continuation ->
            block(object : CustomTarget<Bitmap>() {
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    if (continuation.isActive) {
                        continuation.resume(resource)
                    }
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    if (continuation.isActive) {
                        continuation.resume(null)
                    }
                }

                override fun onLoadCleared(placeholder: Drawable?) { /* do nothing */ }
            })
        }
}
