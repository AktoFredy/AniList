package com.aktofredy.anilist.detail

import androidx.lifecycle.ViewModel
import com.aktofredy.core.domain.model.Anime
import com.aktofredy.core.domain.usecase.AnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val animeUseCase: AnimeUseCase) : ViewModel() {
    fun updateFavoriteAnime(anime: Anime, state: Boolean) = animeUseCase.updateFavoriteAnime(anime, state)
}