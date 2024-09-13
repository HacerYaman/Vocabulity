package com.baitent.vocabulity.ui.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.baitent.vocabulity.common.collect
import com.baitent.vocabulity.data.model.CardItem
import com.baitent.vocabulity.data.source.local.CardRepository
import com.baitent.vocabulity.databinding.FragmentCardsBinding
import com.baitent.vocabulity.ui.Util
import com.lorentzos.flingswipe.SwipeFlingAdapterView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class CardsFragment : Fragment() {

    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CardsViewModel>()
    private lateinit var adapter: CardAdapter
    private val learnedItems = mutableListOf<CardItem>()
    private val notLearnedItems = mutableListOf<CardItem>()

    // Repository kullanımı için
    @Inject
    lateinit var cardRepository: CardRepository

    private var cardItemsList = Util().englishWords.map {
        CardItem(it.key, it.value.first, it.value.second)  // CardItem yapısına uygun hale getir
    }.toMutableList()

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
        adapter = CardAdapter(cardItemsList)
        binding.swipe.adapter = adapter

        binding.swipe.setFlingListener(object : SwipeFlingAdapterView.onFlingListener {
            override fun removeFirstObjectInAdapter() {
                if (cardItemsList.isNotEmpty()) {
                    cardItemsList.removeAt(0)
                    adapter.updateItems(cardItemsList)
                }
            }

            override fun onLeftCardExit(card: Any?) {
                val cardItem = cardItemsList.firstOrNull()
                cardItem?.let {
                    // Öğrenilmemiş olarak işaretle
                    notLearnedItems.add(it)
                    it.status = "notLearned"
                    cardItemsList.remove(it)
                    adapter.updateItems(cardItemsList)
                    // Room veritabanında güncelleme yap
                    lifecycleScope.launch {
                        cardRepository.updateCardStatus(it.engWord, "notLearned")
                    }
                }
            }

            override fun onRightCardExit(card: Any?) {
                val cardItem = cardItemsList.firstOrNull()
                cardItem?.let {
                    // Öğrenildi olarak işaretle
                    learnedItems.add(it)
                    it.status = "learned"
                    cardItemsList.remove(it)
                    adapter.updateItems(cardItemsList)
                    // Room veritabanında güncelleme yap
                    lifecycleScope.launch {
                        cardRepository.updateCardStatus(it.engWord, "learned")
                    }
                }
            }

            override fun onAdapterAboutToEmpty(itemsInAdapter: Int) {}

            override fun onScroll(scrollProgressPercent: Float) {}
        })

        binding.dislike.setOnClickListener {
            binding.swipe.topCardListener.selectLeft()
        }

        binding.like.setOnClickListener {
            binding.swipe.topCardListener.selectRight()
        }
    }

    private fun collectState() {
        viewModel.uiState.collect(viewLifecycleOwner) { state ->
            // UI state'i gerektiği gibi işleyebilirsiniz
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
