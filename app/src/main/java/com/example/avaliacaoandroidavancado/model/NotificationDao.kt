package com.example.avaliacaoandroidavancado.model

import androidx.room.*

@Dao
interface NotificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notification: MyNotifications)

    @Query("SELECT * FROM notifications")
    suspend fun getAllNotifications(): List<MyNotifications>

    @Query("SELECT * FROM notifications WHERE time <= :times")
    suspend fun getNotificationByTime(times: Int): List<MyNotifications>

    @Query("DELETE FROM notifications WHERE time <= :times AND repeat = :repeat")
    suspend fun deleteNotificationByTime(times: Int, repeat: Boolean = false)


}