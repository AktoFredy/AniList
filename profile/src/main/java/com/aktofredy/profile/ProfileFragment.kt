package com.aktofredy.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.aktofredy.core.utils.Constant
import com.aktofredy.profile.databinding.FragmentProfileBinding
import com.bumptech.glide.Glide

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireActivity())
            .load(Constant.PROFILE_URL)
            .into(binding.picProfile)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}