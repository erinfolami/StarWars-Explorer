package com.example.starwarsexplorer.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.starwarsexplorer.data.local.dao.StarshipDao
import com.example.starwarsexplorer.data.local.dao.FilmDao
import com.example.starwarsexplorer.data.local.dao.VehicleDao
import com.example.starwarsexplorer.data.local.entities.StarshipEntity
import com.example.starwarsexplorer.data.local.entities.FilmEntity
import com.example.starwarsexplorer.data.local.entities.VehicleEntity

@Database(
    entities = [StarshipEntity::class, FilmEntity::class, VehicleEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun starshipDao(): StarshipDao

    abstract fun filmDao(): FilmDao

    abstract fun vehicleDao(): VehicleDao
}
