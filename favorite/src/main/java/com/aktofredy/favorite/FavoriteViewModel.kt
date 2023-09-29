package com.aktofredy.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aktofredy.core.domain.usecase.AnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(animeUseCase: AnimeUseCase): ViewModel() {
    val favoriteAnime = animeUseCase.getFavoriteAnime().asLiveData()
}