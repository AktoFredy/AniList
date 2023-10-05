package com.aktofredy.core.domain.repository

import com.aktofredy.core.data.source.Resource
import com.aktofredy.core.domain.model.Anime
import kotlinx.coroutines.flow.Flow

interface IAnimeRepository {
    fun getAllAnime(): Flow<Resource<List<Anime>>>
    fun getFavoriteAnime(): Flow<List<Anime>>
    fun getSearchedAnime(word:String): Flow<List<Anime>>
    fun updateFavoriteAnime(anime: Anime, state: Boolean)
}