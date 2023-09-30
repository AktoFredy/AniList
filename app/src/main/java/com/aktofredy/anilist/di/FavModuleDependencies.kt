package com.aktofredy.anilist.di

import com.aktofredy.core.domain.usecase.AnimeUseCase
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@EntryPoint
@InstallIn(SingletonComponent::class)
interface FavModuleDependencies {
    fun animUseCase(): AnimeUseCase
}