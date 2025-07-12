package com.example.starwarsexplorer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarsexplorer.data.local.entities.StarshipEntity

@Dao
interface StarshipDao {
    @Query("SELECT * FROM starships")
    suspend fun getAllStarships(): List<StarshipEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStarships(starships: List<StarshipEntity>)

    @Query("DELETE FROM starships")
    suspend fun clearStarships()
}