package com.example.countdown2.database

import androidx.room.*
import com.example.countdown2.model.Event
import java.util.*
import kotlinx.coroutines.flow.Flow

@Dao
interface EventDao {

    @Insert
    suspend fun addEvent(event: Event)

    @Delete
    suspend fun deleteEvent(event: Event)

    @Update
    suspend fun updateEvent(event: Event)

    @Query("SELECT * FROM Event WHERE id=(:id)")
    suspend fun getEvent(id: UUID): Event

    @Query("SELECT * FROM Event")
    fun getEvents(): Flow<List<Event>>
}