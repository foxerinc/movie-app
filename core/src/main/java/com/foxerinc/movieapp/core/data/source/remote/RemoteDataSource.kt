package com.foxerinc.movieapp.core.data.source.remote


import com.foxerinc.movieapp.core.data.source.remote.network.ApiResponse
import com.foxerinc.movieapp.core.data.source.remote.response.MovieResponse

import com.foxerinc.movieapp.core.data.source.remote.network.ApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService){


    fun getAllMovie(): Flow<ApiResponse<List<MovieResponse>>> {
        return flow {
            try {
                val client = apiService.getPopularMovies()
                val dataMovie = client.results
                if (dataMovie.isEmpty()){
                    emit(ApiResponse.Empty)
                }else{
                    emit(ApiResponse.Success(dataMovie))
                }
            }catch (e: Exception){
                emit(ApiResponse.Error(e.toString()))
            }
        }.flowOn(Dispatchers.IO)
    }
}