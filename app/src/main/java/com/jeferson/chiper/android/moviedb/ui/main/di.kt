package com.jeferson.chiper.android.moviedb.ui.main

import com.jeferson.chiper.android.moviedb.data.repository.PopularMoviesRepository
import com.jeferson.chiper.android.moviedb.usecases.GetRemoteMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class MainActivityModule {

    @Provides
    @ViewModelScoped
    fun getPopularMoviesUseCaseProvider(
        popularMoviesRepository: PopularMoviesRepository
    ) = GetRemoteMoviesUseCase(popularMoviesRepository)
}