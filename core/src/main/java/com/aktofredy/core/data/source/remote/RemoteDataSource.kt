package com.aktofredy.core.data.source.remote

import android.util.Log
import com.aktofredy.core.data.source.remote.network.ApiResponse
import com.aktofredy.core.data.source.remote.network.ApiService
import com.aktofredy.core.data.source.remote.response.DataItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    suspend fun getListAnimeFromApi(): Flow<ApiResponse<List<DataItem>>> {
        val listAnime = mutableListOf<DataItem>()

        // fetch data from api
        return flow {
            try {
                var page = 1
                do {
                    val response = apiService.getListAnime(page = page)
                    response.data.forEach { anime ->
                        if (!listAnime.contains(anime) && anime.malId != 55016) {
                            listAnime.add(anime)
                        }
                    }
                    page += 1
                    Log.e(TAG, "PAGE : $page")
                } while (response.pagination.currentPage <= 3)

                if (listAnime.isNotEmpty()) {
                    emit(ApiResponse.Success(listAnime))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e: Exception) {
                emit(ApiResponse.Error(e.toString()))
                Log.e(TAG, "getListAnimeData: ${e.message.toString()}")
            }

            Log.e(TAG, "getListAnimeData: $listAnime")

        }.flowOn(Dispatchers.IO)
    }

    companion object {
        private const val TAG = "AnimeRepository"
    }
}