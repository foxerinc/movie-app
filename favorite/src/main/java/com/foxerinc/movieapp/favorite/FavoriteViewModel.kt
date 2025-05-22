package com.foxerinc.movieapp.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.foxerinc.movieapp.core.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


class FavoriteViewModel (movieUseCase: MovieUseCase) : ViewModel()  {
    val favoriteMovie = movieUseCase.getFavoriteMovie().asLiveData()

}