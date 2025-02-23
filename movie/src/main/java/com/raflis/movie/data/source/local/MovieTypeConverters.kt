package com.raflis.movie.data.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class MovieTypeConverters {
    @TypeConverter
    fun fromGenreIdsList(value: List<Int>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toGenreIdsList(value: String): List<Int>? {
        return Gson().fromJson(value, object : TypeToken<List<Int>>() {}.type)
    }
}