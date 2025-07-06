package com.example.starwarsexplorer.data.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.starwarsexplorer.data.local.dao.StarshipDao
import com.example.starwarsexplorer.data.local.database.AppDatabase
import com.example.starwarsexplorer.data.local.entities.StarshipEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class StarshipDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: StarshipDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.starshipDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertStarships_and_getAllStarships_returnsInsertedData() = runBlocking {
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

        dao.insertStarships(starships)
        val result = dao.getAllStarships()

        Assert.assertEquals(1, result.size)
        Assert.assertEquals("X-Wing", result[0].name)
    }
}