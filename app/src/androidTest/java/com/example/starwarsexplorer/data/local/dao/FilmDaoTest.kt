package com.example.starwarsexplorer.data.local.dao


import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.starwarsexplorer.data.local.database.AppDatabase
import com.example.starwarsexplorer.data.local.entities.FilmEntity
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertTrue

@RunWith(AndroidJUnit4::class)
class FilmDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: FilmDao

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(
            context,
            AppDatabase::class.java
        ).allowMainThreadQueries().build()

        dao = database.filmDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun insertFilms_and_getAllFilms_returnsInsertedData() = runBlocking {
        val films = listOf(
            FilmEntity(
                title = "A New Hope",
                episodeId = 4,
                director = "George Lucas",
                producer = "Gary Kurtz, Rick McCallum",
                releaseDate = "1977-05-25",
                openingCrawl = "It is a period of civil war..."
            )
        )

        dao.insertFilms(films)
        val result = dao.getAllFilms()

        Assert.assertEquals(1, result.size)
        Assert.assertEquals("A New Hope", result[0].title)
    }

    @Test
    fun clearAllStarships_deletesAllStarships() = runTest {
        // Given
        val film = FilmEntity(
            title = "A New Hope", episodeId = 4, director = "George Lucas",
            producer = "Gary Kurtz", openingCrawl = "", releaseDate = "1977-05-25"
        )
        dao.insertFilms(listOf(film))

        // When
        dao.clearFilms()

        // Then
        val films = dao.getAllFilms()
        assertTrue(films.isEmpty())
    }
}

