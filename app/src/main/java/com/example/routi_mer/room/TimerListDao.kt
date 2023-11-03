package com.example.routi_mer.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.example.routi_mer.add.TimerList

@Dao
interface TimerListDao {

    @Insert
    fun insertTimer(timer: TimerList)

    @Delete
    fun deleteTimer(timerList: List<TimerList>)

    @Query("SELECT * FROM TimerList")
    fun selectAllTimer(): List<TimerList>

    @Query("SELECT * FROM TimerList WHERE mainId = :mainId")
    fun selectTimerId(mainId: Int): TimerList

    @Query("UPDATE TimerList SET timerTitle = :timerTitle WHERE mainId = :mainId")
    fun updateTitleByTimerId(mainId: Int, timerTitle: String)

    @Query("UPDATE TimerList SET timerDes = :timerDes WHERE mainId = :mainId")
    fun updateDesByTimerId(mainId: Int, timerDes: String)

    @Query("UPDATE TimerList SET timerSec = :timerSec WHERE mainId = :mainId")
    fun updateSecByTimerId(mainId: Int, timerSec: Int)

    @Query("UPDATE TimerList SET oneSetMusicId = :oneSetMusicId WHERE mainId = :mainId")
    fun updateSetMusicByTimerId(mainId: Int, oneSetMusicId: Int)

    @Query("UPDATE TimerList SET fullSetMusicId = :fullSetMusicId WHERE mainId = :mainId")
    fun updateFullSetMusicByTimerId(mainId: Int, fullSetMusicId: String)

}