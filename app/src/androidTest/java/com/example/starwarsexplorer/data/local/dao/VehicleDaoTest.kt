package com.example.starwarsexplorer.data.local.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.starwarsexplorer.data.local.database.AppDatabase
import com.example.starwarsexplorer.data.local.entities.VehicleEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertTrue


@RunWith(AndroidJUnit4::class)
class VehicleDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: VehicleDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.vehicleDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertVehicles_and_getAllVehicles_returnsInsertedData() = runBlocking {
        val vehicles = listOf(
            VehicleEntity(
                name = "Speeder Bike",
                model = "74-Z",
                manufacturer = "Aratech Repulsor Company",
                costInCredits = "8000",
                length = "3",
                crew = "1",
                passengers = "0",
                cargoCapacity = "4"
            )
        )

        dao.insertVehicles(vehicles)
        val result = dao.getAllVehicles()

        Assert.assertEquals(1, result.size)
        Assert.assertEquals("Speeder Bike", result[0].name)
    }

    @Test
    fun clearAllVehicles_deletesAllVehicles() = runBlocking {
        // Given
        val vehicle = VehicleEntity(
            name = "Speeder",
            model = "74-Z",
            manufacturer = "Aratech",
            costInCredits = "3500",
            length = "3.4",
            crew = "1",
            passengers = "1",
            cargoCapacity = "50"
        )
        dao.insertVehicles(listOf(vehicle))

        // When
        dao.clearVehicles()

        // Then - assert the table is empty
        val allVehicles = dao.getAllVehicles()
        assertTrue(allVehicles.isEmpty())
    }
}