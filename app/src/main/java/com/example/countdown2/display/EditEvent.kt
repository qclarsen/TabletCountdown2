package com.example.countdown2.display

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.countdown2.R
import com.example.countdown2.model.Event
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

private const val TAG = "Countdown2"

@Composable
fun EditScreen(
    navController: NavController,
    eventIdString : String?
) {
    val editEventViewModel : EditEventViewModel = viewModel(factory = EditEventViewModelFactory(eventIdString))
    Scaffold(
        topBar = { EditScreenTopBar(navController = navController,editEventViewModel) },
        content = { EditEvent(
            navController = navController,
            editEventViewModel)
        }
    )
}


@Composable
fun EditEvent(navController: NavController, editEventViewModel: EditEventViewModel) {
    val event = editEventViewModel.event.collectAsState()
    Log.d(TAG, "Edit Event ${event.value?.name}")

    Column ( horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize(1.0f)
            .padding(horizontal = 75.dp)
    )
    {
       EventNameTextField(nameText = editEventViewModel.event.collectAsState().value?.name ?: "",
            onChange = { newText -> editEventViewModel.updateEventName(newText) }
         )
        
        DateButton(editEventViewModel = editEventViewModel)

        Button( onClick = {
            // TODO: make this button save the event
            editEventViewModel.saveEvent()
            navController.navigate(Screen.HomeScreen.route)
        }) {
            Text(text = stringResource(R.string.save))
        }
    }

}

@Composable
fun DateButton(editEventViewModel: EditEventViewModel)
{
    val mContext = LocalContext.current

    val currentDate = editEventViewModel.event.collectAsState().value?.date ?: LocalDate.now()

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            editEventViewModel.updateEventDate(LocalDate.of(year,month+1,dayOfMonth))
        },
        currentDate.year,
        currentDate.monthValue-1,
        currentDate.dayOfMonth
    )
    Button( onClick = {
        mDatePickerDialog.show()
    }) {
        Text(text = currentDate.format(DateTimeFormatter.ofPattern(stringResource(R.string.date_pattern)))
        )
    }

}

@Composable
fun EventNameTextField(nameText: String, onChange: (String) -> Unit)
{
    TextField(
        value = nameText,
        onValueChange = onChange,
        placeholder = { Text(text = stringResource(R.string.name)) }
    )
}

@Composable
fun EditScreenTopBar( navController: NavController,editEventViewModel: EditEventViewModel)
{
    TopAppBar( title = { Text(stringResource(R.string.edit_event))},
        actions = {
            IconButton(onClick = {
                editEventViewModel.deleteEvent()
                navController.navigate(Screen.HomeScreen.route )
            }) {
                Icon(Icons.Filled.Delete, stringResource(R.string.delete_event))
            }
        }
    )
}


