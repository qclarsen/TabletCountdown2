package com.example.countdown2.model

import android.content.Context
import android.util.Log
import androidx.room.Room
import com.example.countdown2.database.EventDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.UUID
import kotlinx.coroutines.flow.Flow

private const val DATABASE_NAME = "event-database"
private const val TAG = "Countdown2"

class EventRepository private constructor(
    context: Context,
    private val coroutineScope: CoroutineScope = GlobalScope
) {
    private val database: EventDatabase = Room
        .databaseBuilder(
            context.applicationContext,
            EventDatabase::class.java,
            DATABASE_NAME
        ).build()

    fun getEvents(): Flow<List<Event>> = database.eventDao().getEvents()

    suspend fun getEvent(id: UUID): Event = database.eventDao().getEvent(id)

    fun updateEvent(event: Event) {
        coroutineScope.launch {
            database.eventDao().updateEvent(event)
        }
    }

    fun deleteEvent(event: Event?) {
        if(event != null) {
            coroutineScope.launch {
                database.eventDao().deleteEvent(event)
            }
        }
    }

    fun addEvent(event: Event) {
        coroutineScope.launch {
            database.eventDao().addEvent(event)
        }
        Log.d(TAG, "Adding Event in Repository")
    }

    companion object {
        private var INSTANCE: EventRepository? = null

        fun initialize(context: Context) {
            if(INSTANCE == null) {
                INSTANCE = EventRepository(context)
            }
            Log.d(TAG, "Initializing database.")
        }

        fun get(): EventRepository {
            return INSTANCE ?:
            throw IllegalStateException("EventRepository must be initialized!")
        }
    }

}