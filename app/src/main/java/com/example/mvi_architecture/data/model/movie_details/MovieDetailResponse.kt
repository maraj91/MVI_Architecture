package com.example.mvi_architecture.data.model.movie_details

import com.example.mvi_architecture.domain.model.MovieDetail
import com.example.mvi_architecture.domain.model.Rating
import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("Title") val title: String,
    @SerializedName("Year") val year: String,
    @SerializedName("Rated") val rated: String,
    @SerializedName("Released") val released: String,
    @SerializedName("Runtime") val runtime: String,
    @SerializedName("Genre") val genre: String,
    @SerializedName("Director") val director: String,
    @SerializedName("Writer") val writer: String,
    @SerializedName("Actors") val actors: String,
    @SerializedName("Plot") val plot: String,
    @SerializedName("Language") val language: String,
    @SerializedName("Country") val country: String,
    @SerializedName("Awards") val awards: String,
    @SerializedName("Poster") val poster: String,
    @SerializedName("Ratings") val ratings: List<RatingData>,
    @SerializedName("Metascore") val metascore: String,
    @SerializedName("imdbRating") val imdbRating: String,
    @SerializedName("imdbVotes") val imdbVotes: String,
    @SerializedName("imdbID") val imdbID: String,
    @SerializedName("Type") val type: String,
    @SerializedName("DVD") val dvd: String,
    @SerializedName("BoxOffice") val boxOffice: String,
    @SerializedName("Production") val production: String,
    @SerializedName("Website") val website: String,
    @SerializedName("Response") val response: String
)

data class RatingData(
    @SerializedName("Source") val source: String,
    @SerializedName("Value") val value: String
)

fun MovieDetailResponse.toDomain(): MovieDetail =
    MovieDetail(
        title = title,
        year = year,
        rated = rated,
        released = released,
        runtime = runtime,
        genre = genre,
        director = director,
        writer = writer,
        actors = actors,
        plot = plot,
        language = language,
        country = country,
        awards = awards,
        poster = poster,
        ratings = ratings.map { it.toDomain() },
        metaScore = metascore,
        imdbRating = imdbRating,
        imdbVotes = imdbVotes,
        imdbID = imdbID,
        type = type,
        dvd = dvd,
        boxOffice = boxOffice,
        production = production,
        website = website,
        response = response
    )

fun RatingData.toDomain(): Rating = Rating(source, value)