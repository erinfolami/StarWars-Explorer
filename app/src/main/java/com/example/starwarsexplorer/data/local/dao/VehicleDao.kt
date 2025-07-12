package com.example.starwarsexplorer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarsexplorer.data.local.entities.VehicleEntity

@Dao
interface VehicleDao {
    @Query("SELECT * FROM vehicles")
    suspend fun getAllVehicles(): List<VehicleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicles(vehicles: List<VehicleEntity>)

    @Query("DELETE FROM vehicles")
    suspend fun clearVehicles()
}