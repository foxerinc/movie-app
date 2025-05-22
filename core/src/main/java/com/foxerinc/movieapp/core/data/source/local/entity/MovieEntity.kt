package com.foxerinc.movieapp.core.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity(tableName = "movie")
data class MovieEntity (
    @PrimaryKey
    val movieId: Int,
    @ColumnInfo(name = "movie_name")
    val movieName: String,
    @ColumnInfo(name = "overview")
    val overview: String,
    @ColumnInfo(name = "poster_url")
    val posterUrl: String,
    @ColumnInfo(name = "backdrop_url")
    val backdropUrl: String,
    @ColumnInfo(name = "release_date")
    val releaseDate: String,
    @ColumnInfo(name = "vote_average")
    val voteAverage: Double,
    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean = false
)