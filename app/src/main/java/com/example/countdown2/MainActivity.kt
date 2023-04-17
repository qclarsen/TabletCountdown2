package com.example.countdown2

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.countdown2.display.Navigation


private const val TAG = "Countdown2"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "In Main Activity.")
        setContent {
            Navigation()

        }
    }
}

