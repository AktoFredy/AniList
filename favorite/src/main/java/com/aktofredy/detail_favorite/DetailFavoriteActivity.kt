package com.aktofredy.detail_favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import com.aktofredy.ViewModelFactory
import com.aktofredy.anilist.di.FavModuleDependencies
import com.aktofredy.core.utils.loadImage
import com.aktofredy.favorite.DaggerFavComponent
import com.aktofredy.favorite.databinding.ActivityDetailFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject

class DetailFavoriteActivity : AppCompatActivity() {

    @Inject
    lateinit var factory: ViewModelFactory

    private lateinit var binding: ActivityDetailFavoriteBinding
    private val detFavViewModel: DetailFavoriteViewModel by viewModels {
        factory
    }
    private val args: DetailFavoriteActivityArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        DaggerFavComponent.builder()
            .context(this)
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    this,
                    FavModuleDependencies::class.java
                )
            )
            .build()
            .injectDet(this)
        super.onCreate(savedInstanceState)
        binding = ActivityDetailFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val dataAnime = args.dataAnimeFav
        supportActionBar?.title = dataAnime.title
        binding.tvDetailImage.loadImage(dataAnime.images)
        binding.content.tvDetailSynopsis.text = dataAnime.synopsis

        var statFav = dataAnime.isFavorite
        setFavorite(statFav)
        binding.favBtn.setOnClickListener {
            statFav = !statFav
            detFavViewModel.updateFavAnime(dataAnime, statFav)
            setFavorite(statFav)
        }
    }

    private fun setFavorite(isFav: Boolean) {
        if (isFav) {
            binding.favBtn.setImageDrawable(ContextCompat.getDrawable(this, com.aktofredy.anilist.R.drawable.ic_favorite))
        } else {
            binding.favBtn.setImageDrawable(ContextCompat.getDrawable(this, com.aktofredy.anilist.R.drawable.notfavorite))
        }
    }
}