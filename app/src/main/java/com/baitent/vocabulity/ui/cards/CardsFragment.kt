package com.baitent.vocabulity.ui.cards

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.baitent.vocabulity.R
import com.baitent.vocabulity.common.collect
import com.baitent.vocabulity.data.model.CardItem
import com.baitent.vocabulity.databinding.FragmentCardsBinding
import com.baitent.vocabulity.ui.SharedPreferencesUtil
import com.baitent.vocabulity.ui.Util
import com.lorentzos.flingswipe.SwipeFlingAdapterView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CardsFragment : Fragment() {

    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CardsViewModel>()
    private lateinit var adapter: CardAdapter

    private val learnedItems = mutableListOf<CardItem>()
    private val notLearnedItems = mutableListOf<CardItem>()

    // Map<String, Pair<String, String>> => List<CardItem>
    private var cardItemsList = Util().englishWords.map {
        CardItem(it.key, it.value.first, it.value.second)  // CardItem yapısına uygun hale getir
    }.toMutableList()

    private lateinit var sharedPreferencesUtil: SharedPreferencesUtil

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCardsBinding.inflate(inflater, container, false)
        sharedPreferencesUtil = SharedPreferencesUtil(requireContext())
        loadSavedData()
        setupInfoButton()
        return binding.root
    }

    private fun setupInfoButton() {
        binding.info.setOnClickListener {
            showInfoDialog()
        }
    }

    private fun showInfoDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Nasıl Kullanılır?")
            .setMessage("Kartları elinizle kaydırabilirsiniz. Sağa kaydırırsanız 'Öğrendi' olarak, sola kaydırırsanız 'Öğrenmedi' olarak işaretlenecektir.")
            .setPositiveButton("Anladım") { dialog, _ ->
                dialog.dismiss()
            }
            .setIcon(R.drawable.ic_info)
            .show()
    }

    private fun loadSavedData() {
        learnedItems.addAll(sharedPreferencesUtil.getLearnedCards())
        notLearnedItems.addAll(sharedPreferencesUtil.getNotLearnedCards())
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
                    notLearnedItems.add(it)
                    sharedPreferencesUtil.saveNotLearnedCards(notLearnedItems)
                    cardItemsList.remove(it)
                    adapter.updateItems(cardItemsList)
                }
            }

            override fun onRightCardExit(card: Any?) {
                val cardItem = cardItemsList.firstOrNull()
                cardItem?.let {
                    learnedItems.add(it)
                    sharedPreferencesUtil.saveLearnedCards(learnedItems)
                    cardItemsList.remove(it)
                    adapter.updateItems(cardItemsList)
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
            // Collect UI state if needed
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}