package com.foxerinc.movieapp.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    @SerializedName("results")
    val results: List<MovieResponse>
)