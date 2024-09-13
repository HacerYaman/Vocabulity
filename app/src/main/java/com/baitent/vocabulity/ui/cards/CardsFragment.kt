package com.baitent.vocabulity.ui.cards
import android.os.Bundle
import android.speech.tts.TextToSpeech
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
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
import java.util.Locale
import javax.inject.Inject
import android.app.AlertDialog

@AndroidEntryPoint
class CardsFragment : Fragment(), TextToSpeech.OnInitListener {

    private var _binding: FragmentCardsBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<CardsViewModel>()
    private lateinit var adapter: CardAdapter
    private val learnedItems = mutableListOf<CardItem>()
    private val notLearnedItems = mutableListOf<CardItem>()

    @Inject
    lateinit var cardRepository: CardRepository

    private var cardItemsList = Util().englishWords.map {
        CardItem(it.key, it.value.first, it.value.second)
    }.toMutableList()

    private lateinit var tts: TextToSpeech

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

        tts = TextToSpeech(requireContext(), this)

        binding.info.setOnClickListener {
            showInfoDialog()
        }

        binding.swipe.setOnItemClickListener { _, _, position, _ ->
            val currentCard = cardItemsList[position]
            speak(currentCard.engWord)
        }
    }

    private fun showInfoDialog() {
        AlertDialog.Builder(requireContext())
            .setTitle("Nasıl Kullanılır?")
            .setMessage(
                "• Öğrendi olarak işaretlemek için sağa kaydırın.\n" +
                        "• Öğrenmedi olarak işaretlemek için sola kaydırın.\n" +
                        "• Detay için karta basılı tutun."
            )
            .setPositiveButton("Tamam") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = tts.setLanguage(Locale.US)  // İngilizce dil ayarı
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                println("Dil desteklenmiyor")
            }
        }
    }

    private fun speak(text: String) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        if (::tts.isInitialized) {
            tts.stop()
            tts.shutdown()
        }
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
                    notLearnedItems.add(it) // Yerel listeye ekleme
                    it.status = "notLearned"
                    cardItemsList.remove(it)
                    adapter.updateItems(cardItemsList)

                    // `markAsNotLearned` fonksiyonu çağrılarak veritabanına kaydediliyor
                    viewModel.markAsNotLearned(it)
                }
            }

            override fun onRightCardExit(card: Any?) {
                val cardItem = cardItemsList.firstOrNull()
                cardItem?.let {
                    learnedItems.add(it) // Yerel listeye ekleme
                    it.status = "learned"
                    cardItemsList.remove(it)
                    adapter.updateItems(cardItemsList)

                    // `markAsLearned` fonksiyonu çağrılarak veritabanına kaydediliyor
                    viewModel.markAsLearned(it)
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
}
