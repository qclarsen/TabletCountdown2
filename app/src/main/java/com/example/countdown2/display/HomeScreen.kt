package com.example.countdown2.display

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.countdown2.R


private const val TAG = "Countdown2"

@Composable
fun HomeScreen(
    navController: NavController,
    eventListViewModel: EventListViewModel = viewModel()
) {
    Scaffold(
        topBar = { HomeScreenTopBar(navController = navController) },
        content = { EventList(
            navController = navController,
            events = eventListViewModel.events.collectAsState())}
    )
}

@Composable
fun HomeScreenTopBar( navController: NavController)
{
    TopAppBar( title = { Text("Countdown")},
        actions = {
            IconButton(onClick = {
                navController.navigate(Screen.EditScreen.route )
                }) {
                Icon(Icons.Filled.Add, stringResource(R.string.add_event))
            }
        }
        )

}

