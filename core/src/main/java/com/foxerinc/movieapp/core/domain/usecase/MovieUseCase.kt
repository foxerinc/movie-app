package com.foxerinc.movieapp.core.domain.usecase

import com.foxerinc.movieapp.core.data.Resource
import com.foxerinc.movieapp.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MovieUseCase {
    fun getAllMovie(): Flow<Resource<List<Movie>>>
    fun getFavoriteMovie(): Flow<List<Movie>>
    fun setFavoriteMovie(movie: Movie, state: Boolean)

}