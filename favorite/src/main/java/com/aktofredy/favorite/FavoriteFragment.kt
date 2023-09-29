package com.aktofredy.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.aktofredy.core.domain.model.Anime
import com.aktofredy.core.ui.AnimeAdapter
import com.aktofredy.favorite.databinding.FragmentFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val favoriteViewModel by viewModels<FavoriteViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setLoading(true)

        binding.rvAnimeFav.layoutManager = GridLayoutManager(requireActivity(), 2)

        if (activity != null) {
            favoriteViewModel.favoriteAnime.observe(viewLifecycleOwner) { dataFav ->
                val adapter = AnimeAdapter(dataFav)
                binding.rvAnimeFav.setHasFixedSize(true)
                binding.rvAnimeFav.adapter = adapter
                adapter.setItemClickCallback(object : AnimeAdapter.OnItemClickCallback {
                    override fun onAnimeClicked(data: Anime) {

                    }
                })

                binding.emptyPage.root.visibility = if (dataFav.isNotEmpty()) View.GONE else View.VISIBLE
                binding.emptyPage.tvShownProblem.text = getString(com.aktofredy.core.R.string.empty_page)
                setLoading(false)
            }
        }
    }

    private fun setLoading(state: Boolean) {
        binding.progressBarFav.visibility = if (state) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}