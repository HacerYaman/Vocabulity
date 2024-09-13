package com.baitent.vocabulity.ui.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.baitent.vocabulity.common.collect
import com.baitent.vocabulity.data.model.CardItem
import com.baitent.vocabulity.databinding.FragmentCardsBinding
import com.lorentzos.flingswipe.SwipeFlingAdapterView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardsFragment : Fragment() {

    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CardsViewModel>()
    private lateinit var adapter: CardAdapter

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
        setupSwipeFling()
        collectState()
    }

    private fun setupSwipeFling() {
        adapter = CardAdapter(emptyList())
        binding.swipe.adapter = adapter

        binding.swipe.setFlingListener(object : SwipeFlingAdapterView.onFlingListener {
            override fun removeFirstObjectInAdapter() {
                // Kartı kaldır ve adapteri güncelle
                adapter.notifyDataSetChanged()
            }

            override fun onLeftCardExit(card: Any?) {
                val cardItem = adapter.getItem(0) as CardItem
                viewModel.markCardAsNotLearned(cardItem)
                adapter.notifyDataSetChanged()  // Adapteri güncelle
            }

            override fun onRightCardExit(card: Any?) {
                val cardItem = adapter.getItem(0) as CardItem
                viewModel.markCardAsLearned(cardItem)
                adapter.notifyDataSetChanged()  // Adapteri güncelle
            }

            override fun onAdapterAboutToEmpty(itemsInAdapter: Int) {
                // Adapter boşalmadan hemen önce yapılacak işlemler
            }

            override fun onScroll(scrollProgressPercent: Float) {
                // Kaydırma işlemi sırasında yapılacak işlemler
            }
        })

        // Butonlara tıklanınca kart kaydırma işlemi başlatılıyor
        binding.dislike.setOnClickListener {
            binding.swipe.topCardListener.selectLeft()
        }

        binding.like.setOnClickListener {
            binding.swipe.topCardListener.selectRight()
        }
    }

    private fun collectState() {
        viewModel.uiState.collect(viewLifecycleOwner) { state ->
            if (!state.isLoading) {
                adapter.updateItems(state.notLearnedCards)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

