package com.aktofredy.favorite

import android.content.Context
import com.aktofredy.anilist.di.FavModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [FavModuleDependencies::class])

interface FavComponent {

    fun inject(fragment: FavoriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(favModuleDependencies: FavModuleDependencies): Builder
        fun build(): FavComponent
    }
}