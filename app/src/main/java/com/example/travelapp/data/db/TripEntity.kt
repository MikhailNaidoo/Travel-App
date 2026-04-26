package com.example.travelapp.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "trips")
data class TripEntity(
    @PrimaryKey val id: String,
    val fromCode: String,
    val fromCity: String,
    val toCode: String,
    val toCity: String,
    val departLabel: String,        // "Jun 14"
    val returnLabel: String?,       // "Jun 21" or null
    val dateRangeLabel: String,     // "Jun 14 - Jun 21, 2024"
    val airlineCode: String,
    val flightNumber: String,
    val departTime: String,
    val monthLabel: String,         // "JUN"
    val dayLabel: String,           // "14"
    val isUpcoming: Boolean,
    val createdAt: Long = System.currentTimeMillis()
)
