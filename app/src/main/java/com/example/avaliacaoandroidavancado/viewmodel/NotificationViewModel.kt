package com.example.avaliacaoandroidavancado.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
}