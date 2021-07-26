package com.example.avaliacaoandroidavancado.helper

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.avaliacaoandroidavancado.receiver.AlarmBroadcastReceiver
import java.util.*

class AlarmHelper {
    companion object {
        fun scheduleRTC(
            context: Context,
            alarmManager: AlarmManager,
            hour: Int,
            min: Int,
            repeat: Boolean
        ) {
            val intent = Intent(context, AlarmBroadcastReceiver::class.java)
            val pendingIntent =
                PendingIntent.getBroadcast(context, (0..2147483647).random(), intent, 0)

            val futureTime = Calendar.getInstance().apply {
                set(Calendar.HOUR_OF_DAY, hour)
                set(Calendar.MINUTE, min)
            }

            if (repeat) {
                alarmManager.setRepeating(
                    AlarmManager.RTC_WAKEUP,
                    futureTime.timeInMillis,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )
            } else {
                alarmManager.setExact(AlarmManager.RTC, futureTime.timeInMillis, pendingIntent)
            }

            Log.i("caiango", "rtc criado as $futureTime")

        }
    }
}