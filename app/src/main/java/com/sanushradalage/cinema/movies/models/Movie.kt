package com.sanushradalage.cinema.movies.models

data class Movie(
    val Response: String,
    val Search: List<Search>,
    val totalResults: String
)

data class Search(
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String
)