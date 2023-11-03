package com.example.routi_mer.add

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TimerList(
    @ColumnInfo(name = "routineId") val routineId: Int,
    @ColumnInfo(name = "timerTitle") val timerTitle: String,
    @ColumnInfo(name = "timerDes") val timerDes: String,
    @ColumnInfo(name = "timerSec") val timerSec: Int,
    @ColumnInfo(name = "timerSet") val timerSet: Int,
    @ColumnInfo(name = "oneSetMusicId") val oneSetMusicId: Int,
    @ColumnInfo(name = "fullSetMusicId") val fullSetMusicId: Int,
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "mainId") val mainId: Int = 0
)