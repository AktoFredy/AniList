package com.aktofredy.anilist.di

import com.aktofredy.core.domain.repository.AnimeInteractor
import com.aktofredy.core.domain.usecase.AnimeUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppModule {
    @Binds
    @Singleton
    abstract fun provideAnimeUseCase(animeInteractor: AnimeInteractor): AnimeUseCase
}