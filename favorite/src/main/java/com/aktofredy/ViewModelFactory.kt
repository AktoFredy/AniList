package com.aktofredy

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aktofredy.core.domain.usecase.AnimeUseCase
import com.aktofredy.detail_favorite.DetailFavoriteViewModel
import com.aktofredy.favorite.FavoriteViewModel
import javax.inject.Inject

class ViewModelFactory @Inject constructor(private val animeUseCase: AnimeUseCase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(animeUseCase) as T
            }
            modelClass.isAssignableFrom(DetailFavoriteViewModel::class.java) -> {
                DetailFavoriteViewModel(animeUseCase) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
}