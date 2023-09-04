package com.aktofredy.core.utils

import com.aktofredy.core.data.source.local.entity.AnimeEntity
import com.aktofredy.core.data.source.remote.response.DataItem
import com.aktofredy.core.data.source.remote.response.GenresItem
import com.aktofredy.core.data.source.remote.response.StudiosItem
import com.aktofredy.core.domain.model.Anime

object DataMapper {
    fun mapResponseToEntities(input: List<DataItem>): List<AnimeEntity> {
        val listAnime = ArrayList<AnimeEntity>()
        input.map {
            val anime = AnimeEntity(
                id = it.malId,
                titleJapanese = it.titleJapanese,
                favorites = it.favorites,
                year = it.year,
                rating = it.rating,
                scoredBy = it.scoredBy,
                source = it.source,
                title = it.title,
                type = it.type,
                duration = it.duration,
                score = it.score,
                approved = it.approved,
                genres = listGenreToString(it.genres),
                popularity = it.popularity,
                titleEnglish = it.titleEnglish,
                season = it.season,
                airing = it.airing,
                episodes = it.episodes,
                images = it.images.jpg.largeImageUrl,
                studios = listStudiosToString(it.studios),
                synopsis = it.synopsis,
                url = it.url,
                background = it.background,
                status = it.status,
                trailer = it.trailer.url ?: "?",
            )
            listAnime.add(anime)
        }
        return listAnime
    }

    fun mapEntitiesToDomain(input: List<AnimeEntity>): List<Anime> =
        input.map {
            Anime(
                id = it.id,
                titleJapanese = it.titleJapanese ?: "",
                favorites = it.favorites ?: 0,
                year = it.year ?: 1995,
                rating = it.rating ?: "good",
                scoredBy = it.scoredBy ?: 0,
                source = it.source ?: "-",
                title = it.title,
                type = it.type ?: "?",
                duration = it.duration ?: "-",
                score = it.score,
                approved = it.approved,
                genres = it.genres,
                popularity = it.popularity ?: 0,
                titleEnglish = it.titleEnglish,
                season = it.season ?: "-",
                airing = it.airing,
                episodes = it.episodes ?: 0,
                images = it.images,
                studios = it.studios,
                synopsis = it.synopsis,
                url = it.url,
                background = it.background ?: "-",
                status = it.status,
                trailer = it.trailer ?: "",
                isFavorite = it.isFavorite
            )
        }

    fun mapDomainToEntity(input: Anime) = AnimeEntity(
        id = input.id,
        titleJapanese = input.titleJapanese,
        favorites = input.favorites,
        year = input.year,
        rating = input.rating,
        scoredBy = input.scoredBy,
        source = input.source,
        title = input.title,
        type = input.type,
        duration = input.duration,
        score = input.score,
        approved = input.approved,
        genres = input.genres,
        popularity = input.popularity,
        titleEnglish = input.titleEnglish,
        season = input.season,
        airing = input.airing,
        episodes = input.episodes,
        images = input.images,
        studios = input.studios,
        synopsis = input.synopsis,
        url = input.synopsis,
        background = input.background,
        status = input.status,
        trailer = input.trailer,
        isFavorite = input.isFavorite
    )

    private fun listGenreToString(input: List<GenresItem>): String {
        var genres = ""
        input.forEach { genre ->
            if (genre.name != "null") {
                genres += "${genre.name}, "
            }
        }

        return genres
    }

    private fun listStudiosToString(input: List<StudiosItem>): String {
        var studios = ""
        input.forEach { studio ->
            if (studio.name != "null") {
                studios += "${studio.name}, "
            }
        }

        return studios
    }
}