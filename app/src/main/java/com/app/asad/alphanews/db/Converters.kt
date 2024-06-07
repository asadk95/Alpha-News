package com.app.asad.alphanews.db

import androidx.room.TypeConverter
import com.app.asad.alphanews.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source):String{
        return source.name
    }

    @TypeConverter
    fun toSource(name:String):Source{
        return Source(name,name)
    }
}