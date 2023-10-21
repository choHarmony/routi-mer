package com.example.routi_mer

import androidx.room.TypeConverter
import com.google.gson.Gson

class DataListConverters {
    @TypeConverter
    fun listToJson(value: ArrayList<TimerListData>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): ArrayList<TimerListData> {
        return Gson().fromJson(value, Array<TimerListData>::class.java)?.toList() as ArrayList<TimerListData>
    }
}