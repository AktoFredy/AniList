package com.aktofredy.anilist.detail

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.navArgs
import com.aktofredy.anilist.R
import com.aktofredy.anilist.databinding.ActivityDetailBinding
import com.bumptech.glide.Glide
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private val args: DetailActivityArgs by navArgs()
    private val detailViewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        val dataAnime = args.dataAnime

        supportActionBar?.title = dataAnime.title
        Glide.with(this)
            .load(dataAnime.images)
            .into(binding.tvDetailImage)
        binding.content.tvDetailSynopsis.text = dataAnime.synopsis

        var statFav = dataAnime.isFavorite
        setFavorite(statFav)
        binding.favBtn.setOnClickListener {
            statFav = !statFav
            detailViewModel.updateFavoriteAnime(dataAnime, !dataAnime.isFavorite)
            setFavorite(statFav)
        }
    }

    private fun setFavorite(isFav: Boolean) {
        if (isFav) {
            binding.favBtn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_favorite))
        } else {
            binding.favBtn.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.notfavorite))
        }
    }
}