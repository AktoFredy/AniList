package com.aktofredy.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Anime (
    val id: Int,
    val titleJapanese: String,
    val favorites: Int,
    val year: Int,
    val rating: String,
    val scoredBy: Int,
    val source: String,
    val title: String,
    val type: String,
    val duration: String,
    val score: Float,
    val approved: Boolean,
    val genres: String,
    val popularity: Int,
    val titleEnglish: String,
    val season: String,
    val airing: Boolean,
    val episodes: Int,
    val images: String,
    val studios: String,
    val synopsis: String,
    val url: String,
    val background: String,
    val status: String,
    val trailer: String,
    val isFavorite: Boolean
): Parcelable