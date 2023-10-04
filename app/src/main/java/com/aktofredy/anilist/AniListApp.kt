package com.aktofredy.anilist

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import androidx.preference.PreferenceManager
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
open class AniListApp : Application() {

    override fun onCreate() {
        super.onCreate()

        val pref = PreferenceManager.getDefaultSharedPreferences(applicationContext)
        pref.getBoolean(
            getString(R.string.pref_dark_key),
            false
        ).apply {
            if (this) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }
    }
}