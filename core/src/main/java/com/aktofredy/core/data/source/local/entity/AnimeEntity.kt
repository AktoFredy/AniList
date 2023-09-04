package com.aktofredy.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "anime")
data class AnimeEntity(

    @ColumnInfo("id")
    @PrimaryKey
    val id: Int,

    @ColumnInfo("title_japanese")
    val titleJapanese: String? = "",

    @ColumnInfo("favorites")
    val favorites: Int? = 0,

    @ColumnInfo("year")
    val year: Int? = 1995,

    @ColumnInfo("rating")
    val rating: String? = "good",

    @ColumnInfo("scored_by")
    val scoredBy: Int? = 0,

    @ColumnInfo("source")
    val source: String? = "-",

    @ColumnInfo("title")
    val title: String,

    @ColumnInfo("type")
    val type: String? = "?",

    @ColumnInfo("duration")
    val duration: String? = "-",

    @ColumnInfo("score")
    val score: Float,

    @ColumnInfo("approved")
    val approved: Boolean,

    @ColumnInfo("genres")
    val genres: String,

    @ColumnInfo("popularity")
    val popularity: Int? = 0,

    @ColumnInfo("title_english")
    val titleEnglish: String,

    @ColumnInfo("season")
    val season: String? = "-",

    @ColumnInfo("airing")
    val airing: Boolean,

    @ColumnInfo("episodes")
    val episodes: Int? = 0,

    @ColumnInfo("images")
    val images: String,

    @ColumnInfo("studios")
    val studios: String,

    @ColumnInfo("synopsis")
    val synopsis: String,

    @ColumnInfo("url")
    val url: String,

    @ColumnInfo("background")
    val background: String? = "-",

    @ColumnInfo("status")
    val status: String,

    @ColumnInfo("trailer")
    val trailer: String? = "",

    @ColumnInfo("isFavorite")
    var isFavorite: Boolean = false
)