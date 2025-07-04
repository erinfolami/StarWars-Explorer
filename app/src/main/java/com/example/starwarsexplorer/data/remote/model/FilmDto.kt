package com.example.starwarsexplorer.data.remote.model

import com.google.gson.annotations.SerializedName

data class FilmDto(
    val title: String,
    @SerializedName("episode_id")
    val episodeNumber: Int,
    val director: String,
    val producer: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("opening_crawl")
    val openingCrawl: String
)