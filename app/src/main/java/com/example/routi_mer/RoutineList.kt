package com.example.routi_mer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RoutineList(
    @ColumnInfo(name = "mainTitle") val mainTitle: String,
    @ColumnInfo(name = "mainDes") val mainDes: String,
    @ColumnInfo(name = "routineGroup") val routineGroup: String,
    @ColumnInfo(name = "timerList") val routineTimer: ArrayList<TimerListData>,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "mainId") val mainId: Int = 0
)
