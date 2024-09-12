package com.baitent.vocabulity.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.baitent.vocabulity.databinding.FragmentLearnedBinding
import com.baitent.vocabulity.ui.SharedPreferencesUtil

class LearnedFragment : Fragment() {

    private var _binding: FragmentLearnedBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: LearnedNotLearnedAdapter
    private lateinit var sharedPreferencesUtil: SharedPreferencesUtil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLearnedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        sharedPreferencesUtil = SharedPreferencesUtil(requireContext())
        val learnedItems = sharedPreferencesUtil.getLearnedCards()

        adapter = LearnedNotLearnedAdapter(learnedItems)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferencesUtil = SharedPreferencesUtil(requireContext())
        val learnedItems = sharedPreferencesUtil.getLearnedCards()

        adapter = LearnedNotLearnedAdapter(learnedItems)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
