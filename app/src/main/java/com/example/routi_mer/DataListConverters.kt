package com.example.routi_mer

import androidx.room.TypeConverter
import com.google.gson.Gson

class DataListConverters {
    @TypeConverter
    fun listToJson(value: List<TimerListData>?): String? {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun jsonToList(value: String): List<TimerListData> {
        return Gson().fromJson(value, Array<TimerListData>::class.java).toMutableList()
    }
}