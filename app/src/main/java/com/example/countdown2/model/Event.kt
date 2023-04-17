package com.example.countdown2.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.Period
import java.time.temporal.ChronoUnit
import java.util.UUID

@Entity
data class Event(
    @PrimaryKey val id: UUID,
    val name: String,
    val date: LocalDate,

    //val daysUntil: String = ChronoUnit.DAYS.between(LocalDate.now(), date).toString()
)
