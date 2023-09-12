package com.aktofredy.anilist.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.aktofredy.core.domain.usecase.AnimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(animeUseCase: AnimeUseCase): ViewModel() {
    val anime = animeUseCase.getAllAnime().asLiveData()
}