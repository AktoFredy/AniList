package com.aktofredy.core.data.source.local

import com.aktofredy.core.data.source.local.entity.AnimeEntity
import com.aktofredy.core.data.source.local.room.AnimeDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class LocalDataSource @Inject constructor(private val animeDao: AnimeDao) {

    fun getAllAnime(): Flow<List<AnimeEntity>> = animeDao.getAllAnime()

    fun getFavoriteAnime(): Flow<List<AnimeEntity>> = animeDao.getFavoriteAnime()

    fun getSearchedAnime(word: String): Flow<List<AnimeEntity>> = animeDao.searchAnime(word)

    suspend fun insertAnime(listAnime: List<AnimeEntity>) = animeDao.insertAnime(listAnime)

    fun setFavoriteAnime(anime: AnimeEntity, state: Boolean) {
        anime.isFavorite = state
        animeDao.updateFavoriteAnime(anime)
    }
}