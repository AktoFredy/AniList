package com.aktofredy.anilist.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.aktofredy.anilist.databinding.FragmentHomeBinding
import com.aktofredy.core.data.source.Resource
import com.aktofredy.core.domain.model.Anime
import com.aktofredy.core.ui.AnimeAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvAnime.layoutManager = GridLayoutManager(requireActivity(), 2)

        if (activity != null) {

            homeViewModel.anime.observe(viewLifecycleOwner) { anime ->
                if (anime != null) {
                    when (anime) {
                        is Resource.Loading -> setLoading(true)
                        is Resource.Success -> {
                            setLoading(false)

                            Log.d("fragHome", "onViewCreated: ${anime.data}")

                            val adapter = anime.data?.let { AnimeAdapter(it) }
                            binding.apply {
                                rvAnime.setHasFixedSize(true)
                                rvAnime.adapter = adapter

                                adapter?.setItemClickCallback(object: AnimeAdapter.OnItemClickCallback {
                                    override fun onAnimeClicked(data: Anime) {
                                        val detail = HomeFragmentDirections.actionNavHomeToDetailActivity(data)
                                        detail.dataAnime = data
                                        findNavController().navigate(detail)
                                    }
                                })
                            }
                        }
                        is Resource.Error -> {
                            setLoading(false)
                            binding.errorPage.root.visibility = View.VISIBLE
                            binding.errorPage.tvShownProblem.text = anime.message ?: getString(com.aktofredy.core.R.string.error_page)
                        }
                    }
                }
            }
        }
    }

    private fun setLoading(state: Boolean) {
        binding.progressBar.visibility = if (state) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}