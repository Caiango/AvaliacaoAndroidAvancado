package com.example.avaliacaoandroidavancado.model

import androidx.room.*

@Dao
interface NotificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notification: MyNotifications)

    @Query("SELECT * FROM notifications")
    suspend fun getAllNotifications(): List<MyNotifications>

    @Query("SELECT * FROM notifications WHERE time = :times")
    suspend fun getNotificationByTime(times: String): List<MyNotifications>

    @Query("DELETE FROM notifications WHERE time = :times")
    suspend fun deleteNotificationByTime(times: String)


}