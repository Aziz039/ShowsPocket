package com.example.abdulaziz.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [ShowEntity::class], version = 1, exportSchema = false)
abstract class ShowDatabase: RoomDatabase() {
    abstract fun ShowDAO(): ShowDAO
    companion object {
        @Volatile
        private var INSTANCE: ShowDatabase? = null

        fun getDatabase(context: Context): ShowDatabase {
            val temInstance = INSTANCE
            if (temInstance != null) {
                return temInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShowDatabase::class.java,
                    "show_database").fallbackToDestructiveMigration().build()
                INSTANCE = instance
                return instance
            }
        }
    }
}