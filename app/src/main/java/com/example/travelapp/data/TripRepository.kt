package com.example.travelapp.data

import com.example.travelapp.data.db.TripDao
import com.example.travelapp.data.db.TripEntity
import kotlinx.coroutines.flow.Flow

class TripRepository(private val dao: TripDao) {

    fun upcoming(): Flow<List<TripEntity>> = dao.observeUpcoming()
    fun past(): Flow<List<TripEntity>> = dao.observePast()

    suspend fun add(trip: TripEntity) = dao.upsert(trip)
    suspend fun remove(id: String) = dao.delete(id)

    suspend fun seedIfEmpty() {
        if (dao.count() > 0) return
        dao.upsertAll(seedTrips)
    }

    private val seedTrips: List<TripEntity> = listOf(
        TripEntity(
            id = "t1",
            fromCode = "JFK", fromCity = "New York",
            toCode = "LHR", toCity = "London",
            departLabel = "Jun 14", returnLabel = "Jun 21",
            dateRangeLabel = "Jun 14 - Jun 21, 2024",
            airlineCode = "DL", flightNumber = "DL18",
            departTime = "7:30 AM",
            monthLabel = "JUN", dayLabel = "14",
            isUpcoming = true
        ),
        TripEntity(
            id = "t2",
            fromCode = "LAX", fromCity = "Los Angeles",
            toCode = "NRT", toCity = "Tokyo",
            departLabel = "Jul 10", returnLabel = "Jul 20",
            dateRangeLabel = "Jul 10 - Jul 20, 2024",
            airlineCode = "UA", flightNumber = "UA32",
            departTime = "11:20 PM",
            monthLabel = "JUL", dayLabel = "10",
            isUpcoming = true
        ),
        TripEntity(
            id = "t3",
            fromCode = "MIA", fromCity = "Miami",
            toCode = "CDG", toCity = "Paris",
            departLabel = "Aug 5", returnLabel = "Aug 15",
            dateRangeLabel = "Aug 5 - Aug 15, 2024",
            airlineCode = "AA", flightNumber = "AA94",
            departTime = "6:45 PM",
            monthLabel = "AUG", dayLabel = "5",
            isUpcoming = true
        )
    )
}
