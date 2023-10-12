package com.aktofredy.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.aktofredy.ViewModelFactory
import com.aktofredy.anilist.di.FavModuleDependencies
import com.aktofredy.core.domain.model.Anime
import com.aktofredy.core.ui.AnimeAdapter
import com.aktofredy.favorite.databinding.FragmentFavoriteBinding
import dagger.hilt.android.EntryPointAccessors
import javax.inject.Inject
import com.aktofredy.core.R

class FavoriteFragment : Fragment() {

    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: ViewModelFactory

    private val favoriteViewModel: FavoriteViewModel by viewModels {
        factory
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        DaggerFavComponent.builder()
            .context(requireActivity())
            .appDependencies(
                EntryPointAccessors.fromApplication(
                    requireContext(),
                    FavModuleDependencies::class.java
                )
            )
            .build()
            .inject(this)

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
                        val fav = FavoriteFragmentDirections.actionFavoriteFragmentToDetailFavoriteActivity(data)
                        fav.dataAnimeFav = data
                        findNavController().navigate(fav)
                    }
                })

                binding.emptyPage.root.visibility = if (dataFav.isNotEmpty()) View.GONE else View.VISIBLE
                binding.emptyPage.tvShownProblem.text = getString(R.string.empty_page)
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