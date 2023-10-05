package com.aktofredy.anilist.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aktofredy.core.domain.model.Anime
import com.aktofredy.core.domain.usecase.AnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(animeUseCase: AnimeUseCase): ViewModel() {
    private val data = animeUseCase

    fun searchAnime(word: String) : LiveData<List<Anime>> {
        return data.getSearchedAnime(word).asLiveData()
    }
}