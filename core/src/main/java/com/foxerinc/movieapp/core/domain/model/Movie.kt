package com.foxerinc.movieapp.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie (
    val movieId: Int,
    val movieName: String,
    val overview: String,
    val posterUrl: String,
    val backdropUrl: String,
    val releaseDate: String,
    val voteAverage: Double,
    var isFavorite: Boolean
) : Parcelable
