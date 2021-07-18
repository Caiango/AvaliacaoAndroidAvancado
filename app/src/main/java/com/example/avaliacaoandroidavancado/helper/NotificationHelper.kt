package com.example.avaliacaoandroidavancado.helper

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.avaliacaoandroidavancado.R
import com.example.avaliacaoandroidavancado.model.MyNotifications

class NotificationHelper {

    companion object {

        fun createNotification(context: Context, notification: MyNotifications) {
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannelCompat.Builder("NOTIFICATION CHANNEL", importance)
                .setName("Channel Notification")
                .setDescription("Channel Notification")
                .setLightsEnabled(true)
                .setVibrationEnabled(true)
                .setVibrationPattern(longArrayOf(0, 100, 200, 300))
                .setShowBadge(true)

            NotificationManagerCompat.from(context).createNotificationChannel(channel.build())

            val builder = NotificationCompat.Builder(context, "NOTIFICATION CHANNEL")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentTitle(notification.title)
                .setContentText(notification.text)
                .setStyle(NotificationCompat.BigTextStyle().bigText(notification.text))
                .setAutoCancel(true)

            NotificationManagerCompat.from(context).notify(0, builder.build())
        }
    }
}