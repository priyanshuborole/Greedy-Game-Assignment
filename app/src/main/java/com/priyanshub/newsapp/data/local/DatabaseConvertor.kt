package com.priyanshub.newsapp.data.local

import androidx.room.TypeConverter
import com.priyanshub.newsapp.domain.models.Source

class DatabaseConvertor{

    @TypeConverter
    fun fromSource(source : Source): String {
        return source.name.toString()
    }

    @TypeConverter
    fun toSource(name : String) : Source{
        return Source(name,name)
    }
}