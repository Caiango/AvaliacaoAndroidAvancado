package com.example.avaliacaoandroidavancado.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications")
data class MyNotifications(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val text: String,
    val time: Int,
    val repeat: Boolean,
    val dawn: Boolean,
    val midday: Boolean
)