package com.example.avaliacaoandroidavancado

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.avaliacaoandroidavancado.model.MyNotifications
import com.example.avaliacaoandroidavancado.model.NotificationDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NotificationViewModel : ViewModel() {

    fun insertNotification(notification: MyNotifications, db: NotificationDao) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(notification)
                Log.d("meu banco", db.getAllNotifications().toString())
            }
        }
    }
}