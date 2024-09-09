package com.baitent.vocabulity.ui.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.viewpager2.widget.ViewPager2
import com.baitent.vocabulity.R
import com.baitent.vocabulity.common.collect
import com.baitent.vocabulity.data.model.CardItem
import com.baitent.vocabulity.databinding.FragmentCardsBinding
import com.lorentzos.flingswipe.SwipeFlingAdapterView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CardsFragment : Fragment() {

    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CardsViewModel>()

    private lateinit var adapter: CardAdapter
    private val cardItems = mutableListOf<CardItem>()
    private val selectedItems = mutableListOf<CardItem>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCardsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(binding) {}

        collectState()

        val swipeView: SwipeFlingAdapterView = view.findViewById(R.id.swipe)
        val dislikeButton: Button = view.findViewById(R.id.dislike) //bindinf yap
        val likeButton: Button = view.findViewById(R.id.like)

        adapter = CardAdapter(cardItems)
        swipeView.adapter = adapter

        swipeView.setFlingListener(object : SwipeFlingAdapterView.onFlingListener {
            override fun removeFirstObjectInAdapter() {
                // Remove the first item in the adapter
                cardItems.removeAt(0)
                adapter.notifyDataSetChanged()
            }

            override fun onLeftCardExit(card: Any?) {
                // Handle left swipe (dislike)
            }

            override fun onRightCardExit(card: Any?) {
                // Handle right swipe (like)
            }

            override fun onAdapterAboutToEmpty(itemsInAdapter: Int) {
                // Handle when the adapter is about to be empty
            }

            override fun onScroll(scrollProgressPercent: Float) {
                // Handle scroll progress
            }
    })
        dislikeButton.setOnClickListener {
            swipeView.topCardListener.selectLeft()
        }

        likeButton.setOnClickListener {
            swipeView.topCardListener.selectRight()
        }

        // Load sample data
        loadSampleData()
    }

    private fun loadSampleData() {
        // Add some sample data to the list
        cardItems.addAll(listOf(
            CardItem("Word 1"),
            CardItem("Word 2"),
            CardItem("Word 3")
        ))
        adapter.notifyDataSetChanged()
    }

    private fun collectState() {
        with(binding) {
            viewModel.uiState.collect(viewLifecycleOwner) { state ->

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}