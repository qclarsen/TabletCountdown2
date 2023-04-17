package com.example.countdown2.display

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countdown2.model.Event
import com.example.countdown2.model.EventRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.util.*

private const val TAG = "Countdown2"

class EventListViewModel : ViewModel() {
    private val eventRepository = EventRepository.get()

    private val _events:MutableStateFlow<List<Event>> = MutableStateFlow(emptyList())
    val events: StateFlow<List<Event>>
        get() = _events.asStateFlow()

    var size = _events.value.size

    init {
        Log.d(TAG, "Initializing Event View Model")

        viewModelScope.launch {
            Log.d(TAG, "Collecting events from repository.")
            eventRepository.getEvents().collect {
                _events.value = it
                Log.d(TAG, "Number of events in collect block: ${it.size}")
            }
            Log.d(TAG, "Number of events: $size")

        }
    }
}