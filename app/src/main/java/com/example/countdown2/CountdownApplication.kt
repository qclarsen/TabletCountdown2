package com.example.countdown2

import android.app.Application
import com.example.countdown2.model.EventRepository

class CountdownApplication :Application() {
    override fun onCreate() {
        super.onCreate()
        EventRepository.initialize(this)
    }
}