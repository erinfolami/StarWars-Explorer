package com.example.starwarsexplorer.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "films")
data class FilmEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String,
    val episodeId: Int,
    val director: String,
    val producer: String,
    val releaseDate: String,
    val openingCrawl: String
)