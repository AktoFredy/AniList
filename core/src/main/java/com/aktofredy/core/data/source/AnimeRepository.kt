package com.aktofredy.core.data.source

import com.aktofredy.core.data.source.local.LocalDataSource
import com.aktofredy.core.data.source.remote.RemoteDataSource
import com.aktofredy.core.data.source.remote.network.ApiResponse
import com.aktofredy.core.data.source.remote.response.DataItem
import com.aktofredy.core.domain.model.Anime
import com.aktofredy.core.domain.repository.IAnimeRepository
import com.aktofredy.core.utils.AppExecutors
import com.aktofredy.core.utils.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AnimeRepository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
): IAnimeRepository {
    override fun getAllAnime(): Flow<Resource<List<Anime>>> =
        object : NetworkBoundResource<List<Anime>, List<DataItem>>() {
            override fun loadFromDB(): Flow<List<Anime>> {
                return localDataSource.getAllAnime().map {
                    DataMapper.mapEntitiesToDomain(it)
                }
            }

            override fun shouldFetch(data: List<Anime>?): Boolean = data.isNullOrEmpty()

            override suspend fun createCall(): Flow<ApiResponse<List<DataItem>>> = remoteDataSource.getListAnimeFromApi()

            override suspend fun saveCallResult(data: List<DataItem>) {
                val listAnime = DataMapper.mapResponseToEntities(data)
                localDataSource.insertAnime(listAnime)
            }
        }.asFlow()

    override fun getFavoriteAnime(): Flow<List<Anime>> {
        return localDataSource.getFavoriteAnime().map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun getSearchedAnime(word: String): Flow<List<Anime>> {
        return localDataSource.getSearchedAnime(word).map {
            DataMapper.mapEntitiesToDomain(it)
        }
    }

    override fun updateFavoriteAnime(anime: Anime, state: Boolean) {
        val animeEntity = DataMapper.mapDomainToEntity(anime)
        appExecutors.diskIO().execute { localDataSource.setFavoriteAnime(animeEntity, state) }
    }
}