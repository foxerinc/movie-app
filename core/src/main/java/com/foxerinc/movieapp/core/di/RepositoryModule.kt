package com.foxerinc.movieapp.core.di

import com.foxerinc.movieapp.core.data.MovieRepository
import com.foxerinc.movieapp.core.data.source.local.LocalDataSource
import com.foxerinc.movieapp.core.data.source.remote.RemoteDataSource
import com.foxerinc.movieapp.core.domain.repository.IMovieRepository
import com.foxerinc.movieapp.core.utils.AppExecutors
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideRepository(remote: RemoteDataSource,
                          local: LocalDataSource,
                          executors: AppExecutors
    ): IMovieRepository = MovieRepository(remote, local, executors)
}