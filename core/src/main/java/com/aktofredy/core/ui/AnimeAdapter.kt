package com.aktofredy.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aktofredy.core.databinding.AnimeItemBinding
import com.aktofredy.core.domain.model.Anime
import com.bumptech.glide.Glide
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
            Glide.with(holder.itemView.context)
                .load(anime.images)
                .into(tvAnimeImage)

            tvAnimeTitle.text = anime.title
            tvAnimeType.text = anime.type
            tvAnimeSeason.text = anime.season
        }

        holder.itemView.setOnClickListener {
            onItemClickCallback.onAnimeClicked(listAnime[holder.bindingAdapterPosition])
        }
    }

    override fun getItemCount() = listAnime.size

}