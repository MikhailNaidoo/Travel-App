package com.example.travelapp.data.model

import androidx.compose.ui.graphics.Color

data class Airport(
    val code: String,
    val city: String,
    val name: String,
    val country: String
)

data class Airline(
    val code: String,
    val name: String,
    val accent: Color
)

enum class TripType { RoundTrip, OneWay, MultiCity }

enum class CabinClass(val label: String) {
    Economy("Economy"),
    PremiumEconomy("Premium economy"),
    Business("Business"),
    First("First")
}

data class FlightSearch(
    val from: Airport,
    val to: Airport,
    val depart: String,           // pre-formatted (e.g., "Fri, Jun 14")
    val ret: String?,             // null for one-way
    val travelers: Int,
    val cabin: CabinClass,
    val tripType: TripType
)

enum class FlightBadge { BestOverall, Cheapest, Fastest, None }

data class Flight(
    val id: String,
    val airline: Airline,
    val flightNumber: String,
    val from: Airport,
    val to: Airport,
    val departTime: String,
    val arriveTime: String,
    val arriveNextDay: Boolean,
    val durationMinutes: Int,
    val stops: Int,
    val priceUsd: Int,
    val badge: FlightBadge = FlightBadge.None
) {
    val durationLabel: String
        get() {
            val h = durationMinutes / 60
            val m = durationMinutes % 60
            return if (m == 0) "${h}h" else "${h}h ${m}m"
        }
    val stopsLabel: String
        get() = when (stops) {
            0 -> "Nonstop"
            1 -> "1 stop"
            else -> "$stops stops"
        }
}

data class CityDeal(
    val city: String,
    val country: String,
    val priceFromUsd: Int,
    val durationMinutes: Int,
    val stops: Int,
    val gradient: List<Color>
) {
    val durationLabel: String
        get() {
            val h = durationMinutes / 60
            val m = durationMinutes % 60
            return if (m == 0) "${h}h" else "${h}h ${m}m"
        }
    val stopsLabel: String get() = if (stops == 0) "Nonstop" else "$stops stop"
}

data class DateOption(
    val dayLabel: String,        // "Fri, Jun 14"
    val priceUsd: Int,
    val isSelected: Boolean = false
)

enum class TripStatus { Upcoming, Past }

data class WatchedRoute(
    val id: String,
    val from: Airport,
    val to: Airport,
    val dateRange: String,        // "Jun 14 — Jun 21"
    val cabin: CabinClass,
    val currentPriceUsd: Int,
    val deltaUsd: Int,            // negative = drop, positive = rise, 0 = flat
    val updated: String           // "Today" / "Yesterday"
) {
    val statusLabel: String
        get() = when {
            deltaUsd < 0 -> "Price dropped"
            deltaUsd > 0 -> "Price might increase"
            else -> "No change"
        }
}
