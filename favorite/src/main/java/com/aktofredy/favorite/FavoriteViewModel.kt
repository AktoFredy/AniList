package com.aktofredy.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aktofredy.core.domain.usecase.AnimeUseCase

class FavoriteViewModel (animeUseCase: AnimeUseCase): ViewModel() {
    val favoriteAnime = animeUseCase.getFavoriteAnime().asLiveData()
}