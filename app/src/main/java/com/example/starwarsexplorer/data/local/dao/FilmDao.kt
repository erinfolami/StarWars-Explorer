package com.example.starwarsexplorer.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.starwarsexplorer.data.local.entities.FilmEntity

@Dao
interface FilmDao {
    @Query("SELECT * FROM films")
    suspend fun getAllFilms(): List<FilmEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFilms(films: List<FilmEntity>)

    @Query("DELETE FROM films")
    suspend fun clearFilms()
}