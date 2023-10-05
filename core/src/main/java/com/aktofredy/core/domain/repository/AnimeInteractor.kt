package com.aktofredy.core.domain.repository

import com.aktofredy.core.domain.model.Anime
import com.aktofredy.core.domain.usecase.AnimeUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AnimeInteractor @Inject constructor(private val animeRepo: IAnimeRepository): AnimeUseCase {

    override fun getAllAnime() = animeRepo.getAllAnime()

    override fun getFavoriteAnime() = animeRepo.getFavoriteAnime()

    override fun getSearchedAnime(word: String): Flow<List<Anime>> = animeRepo.getSearchedAnime(word)

    override fun updateFavoriteAnime(anime: Anime, state: Boolean) = animeRepo.updateFavoriteAnime(anime, state)

}