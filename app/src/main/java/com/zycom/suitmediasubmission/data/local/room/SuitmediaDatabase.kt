package com.zycom.suitmediasubmission.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zycom.suitmediasubmission.data.local.entity.RemoteKeys
import com.zycom.suitmediasubmission.data.response.DataItem

@Database(
    entities = [DataItem::class, RemoteKeys::class],
    version = 1,
    exportSchema = false
)
abstract class SuitmediaDatabase : RoomDatabase(){

    abstract fun userDao(): UserDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var instance: SuitmediaDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): SuitmediaDatabase {
            return instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    SuitmediaDatabase::class.java, "user_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
        }
    }
}