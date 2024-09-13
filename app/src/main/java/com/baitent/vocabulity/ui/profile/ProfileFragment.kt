package com.baitent.vocabulity.ui.profile

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.baitent.vocabulity.R
import com.baitent.vocabulity.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var adapter: ProfileAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProfileBinding.bind(view)

        setupRecyclerView()

        // İlk olarak "learned" butonu disable olduğundan, learned kartları yükle
        viewModel.loadLearnedCards()
        observeLearnedCards()

        // "Not Learned" butonuna basıldığında not learned kartlarını yükle
        binding.notLearned.setOnClickListener {
            binding.notLearned.isEnabled = false
            binding.learned.isEnabled = true

            viewModel.loadNotLearnedCards()
            observeNotLearnedCards()
        }

        // "Learned" butonuna basıldığında learned kartlarını yükle
        binding.learned.setOnClickListener {
            binding.learned.isEnabled = false
            binding.notLearned.isEnabled = true

            viewModel.loadLearnedCards()
            observeLearnedCards()
        }
    }

    // RecyclerView'ı ayarlama
    private fun setupRecyclerView() {
        adapter = ProfileAdapter(emptyList())
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    // Learned kartları gözlemleme
    private fun observeLearnedCards() {
        viewModel.learnedCards.observe(viewLifecycleOwner, Observer { cards ->
            adapter.updateItems(cards)
        })
    }

    // Not Learned kartları gözlemleme
    private fun observeNotLearnedCards() {
        viewModel.notLearnedCards.observe(viewLifecycleOwner, Observer { cards ->
            adapter.updateItems(cards)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
