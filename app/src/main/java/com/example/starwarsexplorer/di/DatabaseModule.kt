package com.example.starwarsexplorer.di

import android.content.Context
import androidx.room.Room
import com.example.starwarsexplorer.data.local.dao.FilmDao
import com.example.starwarsexplorer.data.local.dao.StarshipDao
import com.example.starwarsexplorer.data.local.dao.VehicleDao
import com.example.starwarsexplorer.data.local.database.AppDatabase
import com.example.starwarsexplorer.data.local.datasource.LocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "starwars_db")
            .fallbackToDestructiveMigration(false)
            .build()

    @Provides
    fun provideStarshipDao(db: AppDatabase): StarshipDao = db.starshipDao()

    @Provides
    fun provideFilmDao(db: AppDatabase): FilmDao = db.filmDao()

    @Provides
    fun provideVehicleDao(db: AppDatabase): VehicleDao = db.vehicleDao()

    @Provides
    fun provideLocalDataSource(
        starshipDao: StarshipDao,
        filmDao: FilmDao,
        vehicleDao: VehicleDao
    ): LocalDataSource =
        LocalDataSource(starshipDao, filmDao, vehicleDao)
}

