package com.aktofredy.core.data.source.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.aktofredy.core.data.source.local.entity.AnimeEntity

@Database(entities = [AnimeEntity::class], version = 1, exportSchema = false)
abstract class AnimeDatabase : RoomDatabase() {
    abstract fun animeDao(): AnimeDao
}