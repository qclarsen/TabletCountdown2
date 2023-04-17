package com.example.countdown2.display

//import android.graphics.Color
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.countdown2.R
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.countdown2.model.Event
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.UUID
import androidx.compose.ui.graphics.Color
import com.example.countdown2.ui.theme.Purple500

@Composable
fun EventCell(navController: NavController, event: Event) {
    Card(elevation = 10.dp, modifier = Modifier.padding(10.dp)) {
        Column ( horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxSize(1.0f)
                .padding(horizontal = 75.dp)
        )
        {
            Spacer(modifier = Modifier.weight(weight = .3f, true))
            if(ChronoUnit.DAYS.between(LocalDate.now(), event.date) >= 0) {
                Text(
                    text = ChronoUnit.DAYS.between(LocalDate.now(), event.date).toString(),
                    style = MaterialTheme.typography.h1
                )
                Text(
                    text = stringResource(R.string.days_until),
                    style = MaterialTheme.typography.subtitle1
                )
            }
            else //event already happened
            {
                Text(
                    text = (0 - ChronoUnit.DAYS.between(LocalDate.now(), event.date)).toString(),
                    style = MaterialTheme.typography.h1,
                    color = Purple500 //Color.Blue
                )
                Text(
                    text = stringResource(R.string.days_since),
                    style = MaterialTheme.typography.subtitle1
                )

            }
            Text( text = event.name,
                style = MaterialTheme.typography.h4)
            Text( text = event.date.format(DateTimeFormatter.ofPattern(stringResource(R.string.date_pattern))),
                style = MaterialTheme.typography.subtitle1)
            Button(
                onClick = {
                    navController.navigate(Screen.EditScreen.route + "?eventId=" + event.id.toString())
                }

            ) {
                Text (text = stringResource(R.string.edit))
            }
            Spacer(modifier = Modifier.weight(weight = .6f, true))
        }
    }

}
