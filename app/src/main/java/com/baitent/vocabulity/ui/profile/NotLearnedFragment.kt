package com.baitent.vocabulity.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.baitent.vocabulity.databinding.FragmentNotLearnedBinding
import com.baitent.vocabulity.ui.SharedPreferencesUtil

class NotLearnedFragment : Fragment() {

    private var _binding: FragmentNotLearnedBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: LearnedNotLearnedAdapter
    private lateinit var sharedPreferencesUtil: SharedPreferencesUtil

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentNotLearnedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sharedPreferencesUtil = SharedPreferencesUtil(requireContext())
        val notLearnedItems = sharedPreferencesUtil.getNotLearnedCards()

        adapter = LearnedNotLearnedAdapter(notLearnedItems)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
