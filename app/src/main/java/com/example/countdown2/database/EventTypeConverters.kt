package com.example.countdown2.database

import androidx.room.TypeConverter
import java.time.LocalDate

class EventTypeConverters {
    @TypeConverter
    fun fromLocalDate(date: LocalDate): Long {
        return date.toEpochDay()
    }

    @TypeConverter
    fun toLocalDate(millisSinceEpoch: Long): LocalDate {
        return LocalDate.ofEpochDay(millisSinceEpoch)
    }
}