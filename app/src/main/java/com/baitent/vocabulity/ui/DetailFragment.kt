package com.baitent.vocabulity.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.baitent.vocabulity.R
import com.baitent.vocabulity.databinding.FragmentDetailBinding
import com.baitent.vocabulity.ui.detail.DetailFragmentArgs
import com.baitent.vocabulity.ui.detail.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    // ViewModel'i tanımlayın
    private val viewModel: DetailViewModel by viewModels()

    private lateinit var englishWord: String
    private lateinit var turkishWord: String
    private lateinit var exampleSentence: String
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        englishWord = args.englishWord
        turkishWord = args.turkishWord
        exampleSentence = args.exampleSentence

        binding.englishWord.text = englishWord
        binding.turkishWord.text = turkishWord
        binding.exampleSentence.text = exampleSentence

        binding.button.setOnClickListener {
            viewModel.updateCardStatus(englishWord, "learned")
        }

        // "Not Learned" butonuna tıklama işlevi
        binding.button2.setOnClickListener {
            viewModel.updateCardStatus(englishWord, "notLearned")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

