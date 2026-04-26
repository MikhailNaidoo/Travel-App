package com.example.travelapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TripDao {
    @Query("SELECT * FROM trips WHERE isUpcoming = 1 ORDER BY createdAt DESC")
    fun observeUpcoming(): Flow<List<TripEntity>>

    @Query("SELECT * FROM trips WHERE isUpcoming = 0 ORDER BY createdAt DESC")
    fun observePast(): Flow<List<TripEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(trip: TripEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsertAll(trips: List<TripEntity>)

    @Query("DELETE FROM trips WHERE id = :id")
    suspend fun delete(id: String)

    @Query("SELECT COUNT(*) FROM trips")
    suspend fun count(): Int
}
