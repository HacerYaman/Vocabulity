package com.baitent.vocabulity.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.baitent.vocabulity.databinding.FragmentProfileBinding
import com.google.android.material.tabs.TabLayoutMediator

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        // ViewPager ve Adapter'ı ayarla
        val viewPager = binding.viewPager
        val tabLayout = binding.tabLayout

        viewPager.adapter = ViewPagerAdapter(requireActivity())

        // TabLayout ve ViewPager'ı bağla
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            when (position) {
                0 -> tab.text = "Learned"
                1 -> tab.text = "Not Learned"
            }
        }.attach()

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private inner class ViewPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = 2

        override fun createFragment(position: Int): Fragment {
            return when (position) {
                0 -> LearnedFragment()  // Learned tabı
                1 -> NotLearnedFragment()  // Not Learned tabı
                else -> LearnedFragment()
            }
        }
    }
}
