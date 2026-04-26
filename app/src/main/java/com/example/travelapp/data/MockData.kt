package com.example.travelapp.data

import androidx.compose.ui.graphics.Color
import com.example.travelapp.data.model.Airline
import com.example.travelapp.data.model.Airport
import com.example.travelapp.data.model.CabinClass
import com.example.travelapp.data.model.CityDeal
import com.example.travelapp.data.model.DateOption
import com.example.travelapp.data.model.Flight
import com.example.travelapp.data.model.FlightBadge
import com.example.travelapp.data.model.WatchedRoute
import com.example.travelapp.ui.theme.AmericanRed
import com.example.travelapp.ui.theme.DeltaRed
import com.example.travelapp.ui.theme.JetBlueBlue
import com.example.travelapp.ui.theme.UnitedBlue

object MockAirports {
    val JFK = Airport("JFK", "New York", "John F. Kennedy Intl.", "United States")
    val LHR = Airport("LHR", "London", "Heathrow", "United Kingdom")
    val CDG = Airport("CDG", "Paris", "Charles de Gaulle", "France")
    val FCO = Airport("FCO", "Rome", "Fiumicino", "Italy")
    val LAX = Airport("LAX", "Los Angeles", "Los Angeles Intl.", "United States")
    val NRT = Airport("NRT", "Tokyo", "Narita Intl.", "Japan")
    val MIA = Airport("MIA", "Miami", "Miami Intl.", "United States")
    val BCN = Airport("BCN", "Barcelona", "El Prat", "Spain")
    val DXB = Airport("DXB", "Dubai", "Dubai Intl.", "United Arab Emirates")

    val all = listOf(JFK, LHR, CDG, FCO, LAX, NRT, MIA, BCN, DXB)
}

object MockAirlines {
    val Delta = Airline("DL", "Delta Air Lines", DeltaRed)
    val United = Airline("UA", "United Airlines", UnitedBlue)
    val American = Airline("AA", "American Airlines", AmericanRed)
    val JetBlue = Airline("B6", "JetBlue", JetBlueBlue)

    val all = listOf(Delta, United, American, JetBlue)
}

object MockData {

    val topDeals: List<CityDeal> = listOf(
        CityDeal(
            city = "London",
            country = "United Kingdom",
            priceFromUsd = 498,
            durationMinutes = 7 * 60 + 15,
            stops = 0,
            gradient = listOf(Color(0xFF4F86C6), Color(0xFF8FB8E2))
        ),
        CityDeal(
            city = "Paris",
            country = "France",
            priceFromUsd = 550,
            durationMinutes = 7 * 60 + 30,
            stops = 0,
            gradient = listOf(Color(0xFFE8B4A0), Color(0xFFD08770))
        ),
        CityDeal(
            city = "Rome",
            country = "Italy",
            priceFromUsd = 620,
            durationMinutes = 8 * 60 + 5,
            stops = 0,
            gradient = listOf(Color(0xFFF1C76A), Color(0xFFD08F40))
        ),
        CityDeal(
            city = "Barcelona",
            country = "Spain",
            priceFromUsd = 575,
            durationMinutes = 8 * 60 + 20,
            stops = 0,
            gradient = listOf(Color(0xFFEFA86A), Color(0xFFE07A4D))
        ),
        CityDeal(
            city = "Tokyo",
            country = "Japan",
            priceFromUsd = 982,
            durationMinutes = 13 * 60 + 45,
            stops = 1,
            gradient = listOf(Color(0xFFE57373), Color(0xFF8E24AA))
        ),
        CityDeal(
            city = "Dubai",
            country = "UAE",
            priceFromUsd = 845,
            durationMinutes = 12 * 60 + 35,
            stops = 0,
            gradient = listOf(Color(0xFFF59E0B), Color(0xFFB45309))
        )
    )

    val bestDepartingFlights: List<Flight> = listOf(
        Flight(
            id = "DL18",
            airline = MockAirlines.Delta,
            flightNumber = "DL18",
            from = MockAirports.JFK,
            to = MockAirports.LHR,
            departTime = "7:30 AM",
            arriveTime = "7:50 PM",
            arriveNextDay = false,
            durationMinutes = 7 * 60 + 20,
            stops = 0,
            priceUsd = 498,
            badge = FlightBadge.BestOverall
        ),
        Flight(
            id = "AA106",
            airline = MockAirlines.American,
            flightNumber = "AA106",
            from = MockAirports.JFK,
            to = MockAirports.LHR,
            departTime = "9:20 PM",
            arriveTime = "9:40 AM",
            arriveNextDay = true,
            durationMinutes = 7 * 60 + 20,
            stops = 0,
            priceUsd = 512
        ),
        Flight(
            id = "UA80",
            airline = MockAirlines.United,
            flightNumber = "UA80",
            from = MockAirports.JFK,
            to = MockAirports.LHR,
            departTime = "10:15 AM",
            arriveTime = "10:35 PM",
            arriveNextDay = false,
            durationMinutes = 7 * 60 + 20,
            stops = 0,
            priceUsd = 529
        ),
        Flight(
            id = "AA100",
            airline = MockAirlines.American,
            flightNumber = "AA100",
            from = MockAirports.JFK,
            to = MockAirports.LHR,
            departTime = "6:45 PM",
            arriveTime = "7:05 AM",
            arriveNextDay = true,
            durationMinutes = 7 * 60 + 20,
            stops = 0,
            priceUsd = 545
        ),
        Flight(
            id = "B6207",
            airline = MockAirlines.JetBlue,
            flightNumber = "B6207",
            from = MockAirports.JFK,
            to = MockAirports.LHR,
            departTime = "11:55 PM",
            arriveTime = "12:15 PM",
            arriveNextDay = true,
            durationMinutes = 7 * 60 + 20,
            stops = 0,
            priceUsd = 562,
            badge = FlightBadge.Cheapest
        )
    )

    val dateOptions: List<DateOption> = listOf(
        DateOption("Wed, Jun 12", 472),
        DateOption("Thu, Jun 13", 480),
        DateOption("Fri, Jun 14", 498, isSelected = true),
        DateOption("Sat, Jun 15", 505),
        DateOption("Sun, Jun 16", 519),
        DateOption("Mon, Jun 17", 488),
        DateOption("Tue, Jun 18", 510)
    )

    val watchedRoutes: List<WatchedRoute> = listOf(
        WatchedRoute(
            id = "w1",
            from = MockAirports.JFK,
            to = MockAirports.LHR,
            dateRange = "Jun 14 — Jun 21",
            cabin = CabinClass.Economy,
            currentPriceUsd = 498,
            deltaUsd = -52,
            updated = "Today"
        ),
        WatchedRoute(
            id = "w2",
            from = MockAirports.LAX,
            to = MockAirports.NRT,
            dateRange = "Jul 10 — Jul 20",
            cabin = CabinClass.Economy,
            currentPriceUsd = 812,
            deltaUsd = 0,
            updated = "Today"
        ),
        WatchedRoute(
            id = "w3",
            from = MockAirports.MIA,
            to = MockAirports.CDG,
            dateRange = "Aug 5 — Aug 15",
            cabin = CabinClass.Economy,
            currentPriceUsd = 679,
            deltaUsd = 35,
            updated = "Yesterday"
        )
    )
}
