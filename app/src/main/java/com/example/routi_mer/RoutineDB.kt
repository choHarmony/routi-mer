package com.example.routi_mer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RoutineList::class], version = 1)
abstract class RoutineDB: RoomDatabase() {
    abstract fun RoutineListDao(): RoutineListDao

    companion object {
        private var routineListData: RoutineDB? = null

        @Synchronized
        fun getRoutineList(context: Context): RoutineDB? {
            if (routineListData == null) {
                synchronized(RoutineDB::class.java) {
                    routineListData = Room.databaseBuilder(
                        context.applicationContext,
                        RoutineDB::class.java,
                        "RoutineList-DB"
                    ).allowMainThreadQueries().build()
                }
            }
            return routineListData
        }
    }
}