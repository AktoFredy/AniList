package com.aktofredy.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aktofredy.core.R
import com.aktofredy.core.databinding.AnimeItemBinding
import com.aktofredy.core.domain.model.Anime
import com.aktofredy.core.utils.loadImage
import javax.inject.Inject

class AnimeAdapter @Inject constructor(private val listAnime: List<Anime>): RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder>(){

    private lateinit var onItemClickCallback: OnItemClickCallback

    interface OnItemClickCallback {
        fun onAnimeClicked(data: Anime)
    }

    fun setItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    inner class AnimeViewHolder(var binding: AnimeItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AnimeViewHolder {
        return AnimeViewHolder(AnimeItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: AnimeViewHolder, position: Int) {
        val anime = listAnime[position]

        holder.binding.apply {
            tvAnimeImage.loadImage(anime.images)

            tvAnimeTitle.text = anime.title
            tvAnimeType.text = holder.itemView.context.getString(R.string.type_s, anime.type)
            tvAnimeSeason.text = holder.itemView.context.getString(R.string.season_s, anime.season)
        }

        holder.itemView.setOnClickListener {
            onItemClickCallback.onAnimeClicked(listAnime[holder.bindingAdapterPosition])
        }
    }

    override fun getItemCount() = listAnime.size

}