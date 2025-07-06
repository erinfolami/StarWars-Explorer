package com.example.starwarsexplorer.data.remote.api


import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StarWarsApiServiceTest {

    private lateinit var mockWebServer: MockWebServer
    private lateinit var apiService: StarWarsApiService

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.start()
        apiService = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(StarWarsApiService::class.java)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

    @Test
    fun testGetStarships() = runBlocking {
        // Given
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""
                [
                    {
                        "name": "X-Wing",
                        "model": "T-65B",
                        "manufacturer": "Incom Corporation",
                        "cost_in_credits": "149999",
                        "length": "12.5",
                        "crew": "1",
                        "passengers": "0",
                        "cargo_capacity": "110"
                    }
                ]
            """.trimIndent())
        mockWebServer.enqueue(mockResponse)

        // When
        val response = apiService.getStarships()

        // Then
        val request = mockWebServer.takeRequest()
        assertEquals("/starships", request.path)
        assertEquals(200, response.code())
        val starships = response.body()
        assertEquals(1, starships?.size)
        assertEquals("X-Wing", starships?.get(0)?.name)
    }

    @Test
    fun testGetFilms() = runBlocking {
        // Given
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""
                [
                    {
                        "title": "A New Hope",
                        "episode_id": 4,
                        "director": "George Lucas",
                        "producer": "Gary Kurtz, Rick McCallum",
                        "release_date": "1977-05-25",
                        "opening_crawl": "It is a period of civil war..."
                    }
                ]
            """.trimIndent())
        mockWebServer.enqueue(mockResponse)

        // When
        val response = apiService.getFilms()

        // Then
        val request = mockWebServer.takeRequest()
        assertEquals("/films", request.path)
        assertEquals(200, response.code())
        val films = response.body()
        assertEquals(1, films?.size)
        assertEquals("A New Hope", films?.get(0)?.title)
    }

    @Test
    fun testGetVehicles() = runBlocking {
        // Given
        val mockResponse = MockResponse()
            .setResponseCode(200)
            .setBody("""
                [
                    {
                        "name": "Sand Crawler",
                        "model": "Digger Crawler",
                        "manufacturer": "Corellia Mining Corporation",
                        "cost_in_credits": "150000",
                        "length": "36.8",
                        "crew": "46",
                        "passengers": "30",
                        "cargo_capacity": "50000"
                    }
                ]
            """.trimIndent())
        mockWebServer.enqueue(mockResponse)

        // When
        val response = apiService.getVehicles()

        // Given
        val request = mockWebServer.takeRequest()
        assertEquals("/vehicles", request.path)
        assertEquals(200, response.code())
        val vehicles = response.body()
        assertEquals(1, vehicles?.size)
        assertEquals("Sand Crawler", vehicles?.get(0)?.name)
    }
}
