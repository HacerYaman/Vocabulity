package com.baitent.vocabulity.ui.search

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.baitent.vocabulity.data.model.CardItem
import com.baitent.vocabulity.databinding.FragmentSearchBinding
import com.baitent.vocabulity.ui.Util
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: SearchAdapter
    private var wordList: List<CardItem> = Util().englishWords.map {
        CardItem(it.key, it.value.first, "as")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup RecyclerView and Adapter
        adapter = SearchAdapter(wordList)
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter

        // Setup Swipe to Refresh
        binding.swipeRefreshLayout.setOnRefreshListener {
            shuffleWordList()
            binding.swipeRefreshLayout.isRefreshing = false  // Stop the refreshing animation
        }

        // Listen to search input changes
        binding.searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                filterWords(s.toString())
            }

            override fun afterTextChanged(s: Editable?) {}
        })
    }

    private fun shuffleWordList() {
        wordList = wordList.shuffled(Random(System.currentTimeMillis()))
        adapter.updateList(wordList)
    }

    private fun filterWords(query: String) {
        val filteredList = wordList.filter {
            it.engWord.contains(query, ignoreCase = true) || it.trWord.contains(query, ignoreCase = true)
        }
        adapter.updateList(filteredList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
