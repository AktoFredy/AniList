package com.aktofredy.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.aktofredy.core.data.source.local.entity.AnimeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDao {

    @Query("SELECT * FROM anime")
    fun getAllAnime(): Flow<List<AnimeEntity>>

    @Query("SELECT * FROM anime WHERE isFavorite = 1")
    fun getFavoriteAnime(): Flow<List<AnimeEntity>>

    @Query("SELECT * FROM anime WHERE title LIKE '%' || :word || '%'")
    fun searchAnime(word: String): Flow<List<AnimeEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAnime(anime: List<AnimeEntity>)

    @Update
    fun updateFavoriteAnime(anime: AnimeEntity)
}