package com.example.routi_mer.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(entities = [RoutineRoomData::class], version = 2, exportSchema = false)
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
                    ).allowMainThreadQueries().addMigrations(migration_1_2).fallbackToDestructiveMigration().build()
                }
            }
            return routineListData
        }
    }
}

val migration_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE RoutineRoomData Add COLUMN timerList ArrayList NOT NULL default 0")
    }
}