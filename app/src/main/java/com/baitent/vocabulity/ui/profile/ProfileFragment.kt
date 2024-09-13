package com.baitent.vocabulity.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.baitent.vocabulity.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val profileViewModel by viewModels<ProfileViewModel>()
    private lateinit var profileAdapter: ProfileAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupRecyclerView()
        observeLearnedCards()

        profileViewModel.loadLearnedCards()

    }

    private fun setupRecyclerView() {
        profileAdapter = ProfileAdapter(emptyList())
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@ProfileFragment.profileAdapter
        }
    }

    private fun observeLearnedCards() {
        profileViewModel.learnedCards.observe(viewLifecycleOwner, Observer { learnedCards ->
            // Adapter'e gelen verileri güncelle
            profileAdapter.updateItems(learnedCards)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
