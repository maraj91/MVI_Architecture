package com.example.mvi_architecture.data.model.movie

import com.example.mvi_architecture.domain.model.Movie
import com.google.gson.annotations.SerializedName

data class MovieDto(
    @SerializedName("imdbID") val id: String,
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Poster") val poster: String
)

fun MovieDto.toDomain(): Movie =
    Movie(id = id, title = title, year = year, poster = poster)

