package com.example.travelapp

import android.app.Application
import com.example.travelapp.data.TripRepository
import com.example.travelapp.data.db.AppDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class TravelApp : Application() {
    private val appScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)

    val database: AppDatabase by lazy { AppDatabase.get(this) }
    val tripRepository: TripRepository by lazy { TripRepository(database.tripDao()) }

    override fun onCreate() {
        super.onCreate()
        appScope.launch { tripRepository.seedIfEmpty() }
    }
}
