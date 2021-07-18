package com.example.avaliacaoandroidavancado.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.avaliacaoandroidavancado.R

@Database(entities = [MyNotifications::class], version = 1)
abstract class DatabaseInstance : RoomDatabase() {

    abstract val notificationDao: NotificationDao

    companion object {
        @Volatile
        var INSTANCE: DatabaseInstance? = null

        fun getInstance(context: Context): DatabaseInstance? {
            synchronized(this) {
                val instance = INSTANCE

                if (instance == null) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        DatabaseInstance::class.java, context.getString(R.string.mydb)
                    ).fallbackToDestructiveMigration().build()
                }
            }
            return INSTANCE
        }
    }
}
