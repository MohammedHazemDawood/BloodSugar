package com.mhd_07.bloodsugar.data.data_source.local

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneId

class DatabaseConverters {
    @TypeConverter
    fun fromOffsetDateTime(date: OffsetDateTime?): Long? {
        return date?.toInstant()?.toEpochMilli()
    }

    @TypeConverter
    fun toOffsetDateTime(dateTime: Long?): OffsetDateTime {
        return if (dateTime == null) {
            OffsetDateTime.now()
        } else {
            OffsetDateTime.ofInstant(Instant.ofEpochMilli(dateTime), ZoneId.systemDefault())
        }
    }

    @TypeConverter
    fun fromList(list: List<String>?): String {
        return Json.encodeToString(list)
    }

    @TypeConverter
    fun toList(string: String?): List<String>? {
        return string?.let { Json.decodeFromString(it) }
    }
}