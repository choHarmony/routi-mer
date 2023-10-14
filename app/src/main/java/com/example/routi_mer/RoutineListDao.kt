package com.example.routi_mer

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoutineListDao {

    @Insert
    fun insertRoutine(routineList: RoutineList)

    @Delete
    fun deleteRoutine(routineList: List<RoutineList>)

    @Query("SELECT * FROM RoutineList")
    fun selectAllRoutine(): List<RoutineList>

    @Query("SELECT * FROM RoutineList WHERE mainId = :mainId")
    fun selectRoutineId(mainId: Int): RoutineList

    @Query("UPDATE RoutineList SET mainTitle = :mainTitle WHERE mainId = :mainId")
    fun updateTitleByRoutineId(mainId: Int, mainTitle: String)

    @Query("UPDATE RoutineList SET mainDes = :mainDes WHERE mainId = :mainId")
    fun updateDesByRoutineId(mainId: Int, mainDes: String)

    @Query("UPDATE RoutineList SET routineGroup = :routineGroup WHERE mainId = :mainId")
    fun updateGroupByRoutineId(mainId: Int, routineGroup: String)


}