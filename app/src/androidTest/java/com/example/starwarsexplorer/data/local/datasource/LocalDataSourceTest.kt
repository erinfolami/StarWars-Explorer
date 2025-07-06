package com.example.starwarsexplorer.data.local.datasource

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.starwarsexplorer.data.local.database.AppDatabase
import com.example.starwarsexplorer.data.local.entities.FilmEntity
import com.example.starwarsexplorer.data.local.entities.StarshipEntity
import com.example.starwarsexplorer.data.local.entities.VehicleEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LocalDataSourceTest {

    private lateinit var database: AppDatabase
    private lateinit var localDataSource: LocalDataSource

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        localDataSource = LocalDataSource(
            starshipDao = database.starshipDao(),
            filmDao = database.filmDao(),
            vehicleDao = database.vehicleDao()
        )
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun saveStarships_and_getStarships_returnsInsertedData() = runBlocking {
        val starships = listOf(
            StarshipEntity(
                name = "X-Wing",
                model = "T-65B",
                manufacturer = "Incom Corporation",
                costInCredits = "149999",
                length = "12.5",
                crew = "1",
                passengers = "0",
                cargoCapacity = "110"
            )
        )

        localDataSource.saveStarships(starships)
        val result = localDataSource.getStarships()

        Assert.assertEquals(1, result.size)
        Assert.assertEquals("X-Wing", result[0].name)
    }

    @Test
    fun saveFilms_and_getFilms_returnsInsertedData() = runBlocking {
        val films = listOf(
            FilmEntity(
                title = "A New Hope",
                episodeId = 4,
                director = "George Lucas",
                producer = "Gary Kurtz",
                releaseDate = "1977-05-25",
                openingCrawl = "It is a period of civil war..."
            )
        )

        localDataSource.saveFilms(films)
        val result = localDataSource.getFilms()

        Assert.assertEquals(1, result.size)
        Assert.assertEquals("A New Hope", result[0].title)
    }

    @Test
    fun saveVehicles_and_getVehicles_returnsInsertedData() = runBlocking {
        val vehicles = listOf(
            VehicleEntity(
                name = "Sand Crawler",
                model = "Digger Crawler",
                manufacturer = "Corellia Mining Corporation",
                costInCredits = "150000",
                length = "36.8",
                crew = "46",
                passengers = "30",
                cargoCapacity = "50000"
            )
        )

        localDataSource.saveVehicles(vehicles)
        val result = localDataSource.getVehicles()

        Assert.assertEquals(1, result.size)
        Assert.assertEquals("Sand Crawler", result[0].name)
    }
}
