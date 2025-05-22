package com.foxerinc.movieapp.core.data.source.remote.network

import com.foxerinc.movieapp.core.data.source.remote.response.MovieListResponse
import retrofit2.http.GET
import retrofit2.Call

interface ApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(): MovieListResponse
}