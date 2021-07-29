package com.example.avaliacaoandroidavancado.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.avaliacaoandroidavancado.model.DatabaseInstance
import com.example.avaliacaoandroidavancado.model.MyNotifications
import com.example.avaliacaoandroidavancado.model.NotificationDao
import com.example.avaliacaoandroidavancado.viewmodel.NotificationViewModel
import java.util.*

class AlarmBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.i("caiango", "Received broadcast from AlarmManager")

        val db: NotificationDao? = DatabaseInstance.getInstance(context!!)?.notificationDao
        val exactHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val exactMinute = Calendar.getInstance().get(Calendar.MINUTE)
        val exactTime = "$exactHour$exactMinute".toInt()

        NotificationViewModel().getNotificationByTime(db!!, exactTime, context)
    }
}