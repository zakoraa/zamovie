package com.raflis.tv_series.data.source.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TvSeriesTypeConverter {
    @TypeConverter
    fun fromGenreIdsList(value: List<Int>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toGenreIdsList(value: String): List<Int>? {
        return Gson().fromJson(value, object : TypeToken<List<Int>>() {}.type)
    }

    @TypeConverter
    fun fromOriginCountryList(value: List<String>?): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toOriginCountryList(value: String): List<String>? {
        return Gson().fromJson(value, object : TypeToken<List<String>>() {}.type)
    }
}
