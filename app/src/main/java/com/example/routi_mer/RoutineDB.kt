package com.example.routi_mer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters

@Database(entities = [RoutineList::class], version = 1)
@TypeConverters(DataListConverters::class)
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