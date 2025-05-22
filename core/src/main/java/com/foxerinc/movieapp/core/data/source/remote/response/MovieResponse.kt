package com.foxerinc.movieapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieResponse (
    @SerializedName("id")
    val id: Int,

    @SerializedName("title")
    val name: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("backdrop_path")
    val backdropPath: String,

    @SerializedName("release_date")
    val releaseDate: String,

    @SerializedName("vote_average")
    val voteAverage: Double
)