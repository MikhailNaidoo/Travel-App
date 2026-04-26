package com.example.travelapp.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.travelapp.data.MockAirports
import com.example.travelapp.data.model.Airport
import com.example.travelapp.data.model.CabinClass
import com.example.travelapp.data.model.TripType

class SearchState {
    var tripType by mutableStateOf(TripType.RoundTrip)
    var from by mutableStateOf<Airport>(MockAirports.JFK)
    var to by mutableStateOf<Airport>(MockAirports.LHR)
    var depart by mutableStateOf("Fri, Jun 14")
    var ret by mutableStateOf<String?>("Fri, Jun 21")
    var travelers by mutableStateOf(1)
    var cabin by mutableStateOf(CabinClass.Economy)

    fun swap() {
        val tmp = from
        from = to
        to = tmp
    }
}

val LocalSearchState = compositionLocalOf<SearchState> { error("SearchState not provided") }

@Composable
fun rememberSearchState(): SearchState = remember { SearchState() }
