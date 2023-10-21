package com.example.routi_mer

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface RoutineListDao {

    @Insert
    fun insertRoutine(routineList: RoutineRoomData)

    @Delete
    fun deleteRoutine(routineList: List<RoutineRoomData>)

    @Query("SELECT * FROM RoutineRoomData")
    fun selectAllRoutine(): List<RoutineRoomData>

    @Query("SELECT * FROM RoutineRoomData WHERE mainId = :mainId")
    fun selectRoutineId(mainId: Int): RoutineRoomData

    @Query("UPDATE RoutineRoomData SET mainTitle = :mainTitle WHERE mainId = :mainId")
    fun updateTitleByRoutineId(mainId: Int, mainTitle: String)

    @Query("UPDATE RoutineRoomData SET mainDes = :mainDes WHERE mainId = :mainId")
    fun updateDesByRoutineId(mainId: Int, mainDes: String)

    @Query("UPDATE RoutineRoomData SET routineGroup = :routineGroup WHERE mainId = :mainId")
    fun updateGroupByRoutineId(mainId: Int, routineGroup: String)


}