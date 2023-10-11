package com.aktofredy.detail_favorite

import androidx.lifecycle.ViewModel
import com.aktofredy.core.domain.model.Anime
import com.aktofredy.core.domain.usecase.AnimeUseCase

class DetailFavoriteViewModel (private val animeUseCase: AnimeUseCase) : ViewModel() {
    fun updateFavAnime(anime: Anime, state: Boolean) = animeUseCase.updateFavoriteAnime(anime, state)
}