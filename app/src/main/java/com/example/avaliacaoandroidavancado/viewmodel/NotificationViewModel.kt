package com.example.avaliacaoandroidavancado.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avaliacaoandroidavancado.helper.NotificationHelper
import com.example.avaliacaoandroidavancado.model.MyNotifications
import com.example.avaliacaoandroidavancado.model.NotificationDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class NotificationViewModel : ViewModel() {

    val notificationList: MutableLiveData<List<MyNotifications>> = MutableLiveData()

    fun insertNotification(notification: MyNotifications, db: NotificationDao) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(notification)
                Log.d("mydb", "successful insert")
                getAllNotifications(db)
            }
        }
    }

    fun getAllNotifications(db: NotificationDao) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                notificationList.postValue(db.getAllNotifications())
            }
        }

    }

    fun getNotificationByTime(db: NotificationDao, time: Int, context: Context) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val list = db.getNotificationByTime(time)
                withContext(Dispatchers.Main) {
                    showNotification(list, context, time)
                }
            }
        }
    }

    fun deleteNotificationByTime(db: NotificationDao, time: Int) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.deleteNotificationByTime(time)
            }
        }
    }

    private fun showNotification(
        notifications: List<MyNotifications>,
        context: Context,
        time: Int
    ) {
        if (notifications.isNotEmpty()) {
            val hour = Calendar.getInstance()[Calendar.HOUR_OF_DAY]

            if (notifications.last().midday) {
                NotificationHelper.createNotification(context, notifications.last(), time)
            } else if (notifications.last().dawn && hour <= 1 && !notifications.last().midday) {
                NotificationHelper.createNotification(context, notifications.last(), time)
            } else if (!notifications.last().dawn && !notifications.last().midday && hour > 1) {
                NotificationHelper.createNotification(context, notifications.last(), time)
            }

        }
    }

}