package com.example.starwarsexplorer.data.remote.api


import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.test.assertNotNull

@OptIn(ExperimentalCoroutinesApi::class)
class ApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: ApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()

        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/")) // Point to mock server
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun `getStarships returns correct data`() = runTest {
        // Given
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(
                """
                {
                  "count": 1,
                  "next": null,
                  "previous": null,
                  "results": [
                    {
                      "name": "X-Wing",
                      "model": "T-65B",
                      "manufacturer": "Incom Corporation",
                      "cost_in_credits": "149999",
                      "length": "12.5",
                      "max_atmosphering_speed": "1050",
                      "crew": "1",
                      "passengers": "0",
                      "cargo_capacity": "110",
                      "consumables": "1 week",
                      "hyperdrive_rating": "1.0",
                      "MGLT": "100",
                      "starship_class": "Starfighter",
                      "pilots": [],
                      "films": ["https://swapi.dev/api/films/1/"],
                      "created": "2023-01-01T00:00:00Z",
                      "edited": "2023-01-01T00:00:00Z",
                      "url": "https://swapi.dev/api/starships/12/"
                    }
                  ]
                }
                """.trimIndent()
            )

        mockWebServer.enqueue(mockResponse)

        // When
        val response = apiService.getStarships()

        // Then
        assertTrue(response.isSuccessful)
        val body = response.body()
        assertNotNull(body)
        assertEquals(1, body.results.size)
        assertEquals("X-Wing", body.results.first().name)
    }

    @Test
    fun `getFilms returns correct data`() = runTest {
        // Given
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(
                """
                {
                  "count": 1,
                  "next": null,
                  "previous": null,
                  "results": [
                    {
                      "title": "A New Hope",
                      "episode_id": 4,
                      "opening_crawl": "...",
                      "director": "George Lucas",
                      "producer": "Gary Kurtz",
                      "release_date": "1977-05-25",
                      "characters": [],
                      "planets": [],
                      "starships": [],
                      "vehicles": [],
                      "species": [],
                      "created": "",
                      "edited": "",
                      "url": ""
                    }
                  ]
                }
                """.trimIndent()
            )

        mockWebServer.enqueue(mockResponse)

        // When
        val response = apiService.getFilms()

        // Then
        assertTrue(response.isSuccessful)
        val body = response.body()
        assertNotNull(body)
        assertEquals(1, body.results.size)
        assertEquals("A New Hope", body.results.first().title)
    }

    @Test
    fun `getVehicles returns correct data`() = runTest {
        // Given
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody(
                """
                {
                  "count": 1,
                  "next": null,
                  "previous": null,
                  "results": [
                    {
                      "name": "Speeder",
                      "model": "74-Z",
                      "manufacturer": "Aratech",
                      "cost_in_credits": "8000",
                      "length": "3.4",
                      "max_atmosphering_speed": "360",
                      "crew": "1",
                      "passengers": "0",
                      "cargo_capacity": "4",
                      "consumables": "1 day",
                      "vehicle_class": "Speeder",
                      "pilots": [],
                      "films": [],
                      "created": "",
                      "edited": "",
                      "url": ""
                    }
                  ]
                }
                """.trimIndent()
            )

        mockWebServer.enqueue(mockResponse)

        // When
        val response = apiService.getVehicles()

        // Then
        assertTrue(response.isSuccessful)
        val body = response.body()
        assertNotNull(body)
        assertEquals(1, body.results.size)
        assertEquals("Speeder", body.results.first().name)
    }
}
