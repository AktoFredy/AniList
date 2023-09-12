package com.aktofredy.core.di

import com.aktofredy.core.data.source.AnimeRepository
import com.aktofredy.core.domain.repository.IAnimeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepos(animeRepository: AnimeRepository): IAnimeRepository
}