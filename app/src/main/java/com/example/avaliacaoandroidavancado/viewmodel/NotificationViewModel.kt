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

    fun getNotificationByTime(db: NotificationDao, time: String, context: Context) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val list = db.getNotificationByTime(time)
                withContext(Dispatchers.Main) {
                    showNotification(list, context, time)
                }
            }
        }
    }

    fun deleteNotificationByTime(db: NotificationDao, time: String) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.deleteNotificationByTime(time)
            }
        }
    }

    private fun showNotification(
        notifications: List<MyNotifications>,
        context: Context,
        time: String
    ) {
        if (notifications.isNotEmpty()) {
            NotificationHelper.createNotification(context, notifications.last(), time)
        }
    }

}