package com.aktofredy.anilist.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.aktofredy.anilist.R
import com.aktofredy.anilist.databinding.ActivitySearchBinding
import com.aktofredy.core.domain.model.Anime
import com.aktofredy.core.ui.AnimeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySearchBinding
    private val searchViewModel: SearchViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.rvAnime.layoutManager = GridLayoutManager(this, 2)

        binding.emptySearch.root.visibility = View.VISIBLE
        binding.emptySearch.tvShownProblem.text = getString(R.string.initial_search_view)

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText
                .setOnEditorActionListener{ textView, actionId, event ->
                    searchBar.text = searchView.text
                    searchView.hide()
                    searchViewModel.searchAnime(searchBar.text.toString()).observe(this@SearchActivity) { data ->
                        val adapter = AnimeAdapter(data)
                        rvAnime.setHasFixedSize(true)
                        rvAnime.adapter = adapter
                        adapter.setItemClickCallback(object: AnimeAdapter.OnItemClickCallback {
                            override fun onAnimeClicked(data: Anime) {
                                //not enabled for now
                            }
                        })
                        emptySearch.root.visibility = if (data.isNotEmpty()) View.GONE else View.VISIBLE
                        emptySearch.tvShownProblem.text = getString(R.string.norelated_search)
                    }
                    Toast.makeText(this@SearchActivity, searchView.text, Toast.LENGTH_SHORT).show()
                    false
                }
        }
    }
}