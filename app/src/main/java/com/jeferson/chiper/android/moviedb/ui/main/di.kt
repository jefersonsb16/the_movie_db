package com.jeferson.chiper.android.moviedb.ui.main

import com.jeferson.chiper.android.moviedb.data.repository.PopularMoviesRepository
import com.jeferson.chiper.android.moviedb.usecases.GetRemoteMoviesUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
class MainActivityModule {

    @Provides
    fun getPopularMoviesUseCaseProvider(
        popularMoviesRepository: PopularMoviesRepository
    ) = GetRemoteMoviesUseCase(popularMoviesRepository)

    @Provides
    fun popularMoviesViewModelProvider(
        getPopularMoviesUseCase: GetRemoteMoviesUseCase
    ) = PopularMoviesViewModel(getPopularMoviesUseCase)
}