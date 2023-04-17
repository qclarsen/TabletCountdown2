package com.example.countdown2.display

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
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

class EditEventViewModel(private val eventId: UUID?) : ViewModel() {
    private val eventRepository = EventRepository.get()

    private val _event: MutableStateFlow<Event?> = MutableStateFlow(null)
    val event: StateFlow<Event?> = _event.asStateFlow()

    init {
        Log.d(TAG, "Initializing Edit Event View Model")
        // make sure event isn't null so there aren't problems while waiting for the database query
        _event.value = Event(
            id = UUID.randomUUID(),
            name = "",
            date = LocalDate.now()
        )
        eventId?.let {
            viewModelScope.launch {
                _event.value = eventRepository.getEvent(eventId)
            }
        }

    }

    fun updateEventName(nameText: String)
    {
        _event.value = _event.value?.copy(name = nameText)
    }
    fun updateEventDate(newDate: LocalDate)
    {
        _event.value = _event.value?.copy(date = newDate)
    }
    fun saveEvent()
    {
        if(eventId != null )
            _event.value?.let { eventRepository.updateEvent(it)}
        else
            _event.value?.let { eventRepository.addEvent(it)}
    }
    fun deleteEvent()
    {
        if(eventId != null)
            _event.value?.let { eventRepository.deleteEvent(it) }
    }
}

class EditEventViewModelFactory(private val eventIdString: String?) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(eventIdString != null && eventIdString != "")
                EditEventViewModel(UUID.fromString(eventIdString)) as T
            else
                EditEventViewModel(null) as T
    }
}