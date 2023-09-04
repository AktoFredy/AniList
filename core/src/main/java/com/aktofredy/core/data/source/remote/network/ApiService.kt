package com.aktofredy.core.data.source.remote.network

import com.aktofredy.core.data.source.remote.response.AnimeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("anime")
    suspend fun getListAnime(
        @Query("q") title: String? = null,
        @Query("min_score") score: String = "8.70",
        @Query("page") page: Int
    ): AnimeResponse
}