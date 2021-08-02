package com.example.avaliacaoandroidavancado.helper

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import androidx.core.app.NotificationChannelCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.avaliacaoandroidavancado.R
import com.example.avaliacaoandroidavancado.model.DatabaseInstance
import com.example.avaliacaoandroidavancado.model.MyNotifications
import com.example.avaliacaoandroidavancado.model.NotificationDao
import com.example.avaliacaoandroidavancado.ui.MainActivity
import com.example.avaliacaoandroidavancado.viewmodel.NotificationViewModel

object NotificationHelper {


    fun createNotification(context: Context, notification: MyNotifications, time: String) {
        val intentOpen = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
        }

        val openPending =
            PendingIntent.getActivity(context, 0, intentOpen, PendingIntent.FLAG_UPDATE_CURRENT)

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
            .setSmallIcon(R.drawable.ic_alarm)
            .setLargeIcon(
                BitmapFactory.decodeResource(context.resources, R.drawable.ic_alarm)
            )
            .setContentTitle(notification.title)
            .setContentText(notification.text)
            .setStyle(NotificationCompat.BigTextStyle().bigText(notification.text))
            .setAutoCancel(true)
            .setContentIntent(openPending)

        NotificationManagerCompat.from(context).notify(0, builder.build())

        val db: NotificationDao? = DatabaseInstance.getInstance(context)?.notificationDao
        NotificationViewModel().deleteNotificationByTime(db!!, time)
    }
}