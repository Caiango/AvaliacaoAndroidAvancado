package com.example.avaliacaoandroidavancado.model

import androidx.room.*

@Dao
interface NotificationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(notification: MyNotifications)

    @Delete
    suspend fun delete(notification: MyNotifications)

    @Query("SELECT * FROM notifications")
    suspend fun getAllNotifications(): List<MyNotifications>

}