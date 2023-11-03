package com.example.routi_mer.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.routi_mer.add.TimerList

@Database(entities = [TimerList::class], version = 1)
abstract class TimerDB: RoomDatabase() {
    abstract fun TimerListDao(): TimerListDao

    companion object {
        private var timerListData: TimerDB? = null

        @Synchronized
        fun getTimerList(context: Context): TimerDB? {
            if (timerListData == null) {
                synchronized(TimerDB::class.java) {
                    timerListData = Room.databaseBuilder(
                        context.applicationContext,
                        TimerDB::class.java,
                        "TimerList-DB"
                    ).allowMainThreadQueries().build()
                }
            }
            return timerListData
        }
    }
}