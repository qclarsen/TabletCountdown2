package com.example.countdown2.display


import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.countdown2.model.Event
import kotlinx.coroutines.flow.StateFlow
import java.time.LocalDate
import java.util.*

private const val TAG = "Countdown2"

@Composable
fun EventList(
    navController: NavController,
    events: State<List<Event>>
    ) {

    Log.d(TAG, "In Event List.")
    LazyRow() {
        Log.d(TAG, "Number of events in LazyRow: ${events.value.size}.")
        items(events.value) { event ->
            EventCell(navController, event)
        }
    }

}