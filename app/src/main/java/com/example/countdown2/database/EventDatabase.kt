package com.example.countdown2.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.countdown2.model.Event

@Database(entities = [ Event::class], version = 1)
@TypeConverters(EventTypeConverters::class)
abstract class EventDatabase : RoomDatabase() {
    abstract fun eventDao(): EventDao
}