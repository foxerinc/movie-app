package com.foxerinc.movieapp.core.utils

import com.foxerinc.movieapp.core.data.source.local.entity.MovieEntity
import com.foxerinc.movieapp.core.data.source.remote.response.MovieResponse
import com.foxerinc.movieapp.core.domain.model.Movie

object DataMapper {
    fun mapResponsesToEntities(input: List<MovieResponse>): List<MovieEntity>{
        val movieList = ArrayList<MovieEntity>()

        input.map {
            val movie = MovieEntity(
                movieId = it.id,
                movieName = it.name,
                overview = it.overview,
                posterUrl = "https://image.tmdb.org/t/p/original${it.posterPath}",
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                isFavorite = false,
                backdropUrl = "https://image.tmdb.org/t/p/original${it.backdropPath}"
            )
            movieList.add(movie)
        }
        return movieList
    }

    fun mapEntitiesToDomain(input: List<MovieEntity>): List<Movie> =
        input.map {
            Movie(
                movieId = it.movieId,
                movieName = it.movieName,
                overview = it.overview,
                posterUrl = it.posterUrl,
                releaseDate = it.releaseDate,
                voteAverage = it.voteAverage,
                isFavorite = it.isFavorite,
                backdropUrl = it.backdropUrl
            )
        }

    fun mapDomainToEntity(input: Movie) = MovieEntity(
        movieId = input.movieId,
        movieName = input.movieName,
        overview = input.overview,
        posterUrl = input.posterUrl,
        releaseDate = input.releaseDate,
        voteAverage = input.voteAverage,
        isFavorite = input.isFavorite,
        backdropUrl = input.backdropUrl
    )
}