package com.foxerinc.movieapp.detail

import androidx.lifecycle.ViewModel
import com.foxerinc.movieapp.core.data.MovieRepository
import com.foxerinc.movieapp.core.domain.model.Movie
import com.foxerinc.movieapp.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class DetailMovieViewModel @Inject constructor(private val movieUseCase: MovieUseCase) : ViewModel() {
    fun setFavoriteMovie(movie: Movie, newState: Boolean) =
        movieUseCase.setFavoriteMovie(movie, newState)

}