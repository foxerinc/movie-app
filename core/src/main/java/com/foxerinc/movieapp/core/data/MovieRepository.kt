package com.foxerinc.movieapp.core.data


import com.foxerinc.movieapp.core.data.source.local.LocalDataSource
import com.foxerinc.movieapp.core.data.source.remote.RemoteDataSource
import com.foxerinc.movieapp.core.data.source.remote.network.ApiResponse
import com.foxerinc.movieapp.core.data.source.remote.response.MovieResponse
import com.foxerinc.movieapp.core.domain.model.Movie
import com.foxerinc.movieapp.core.domain.repository.IMovieRepository
import com.foxerinc.movieapp.core.utils.AppExecutors
import com.foxerinc.movieapp.core.utils.DataMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IMovieRepository {

    override fun getAllMovie(): Flow<Resource<List<Movie>>> =
        object : NetworkBoundResource<List<Movie>, List<MovieResponse>>(){
            override fun loadFromDB(): Flow<List<Movie>> {
                return localDataSource.getAllMovie().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Movie>?): Boolean =
                data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovie()

            override suspend fun saveCallResult(data: List<MovieResponse>) {
                val movieList = DataMapper.mapResponsesToEntities(data)
                withContext(Dispatchers.IO){
                    localDataSource.insertMovie(movieList)
                }
            }

        }.asFlow()

    override fun getFavoriteMovie(): Flow<List<Movie>>{
        return localDataSource.getFavoriteMovie().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun setFavoriteMovie(movie: Movie, state: Boolean){
        val movieEntity = DataMapper.mapDomainToEntity(movie)
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movieEntity, state) }
    }
}