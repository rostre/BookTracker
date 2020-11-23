package ro.twodoors.booktracker.ui.reading.status

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ro.twodoors.booktracker.databinding.FragmentReadingStatusBinding

class ReadingStatusFragment : Fragment() {

    private lateinit var binding : FragmentReadingStatusBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentReadingStatusBinding.inflate(layoutInflater)
        setupAdapter()
        return binding.root
    }

    private fun setupAdapter() {
        binding.viewPager.adapter =
            ReadingPagerAdapter(
                this.childFragmentManager,
                4
            )
        binding.tabLayout.setupWithViewPager(binding.viewPager, true)
    }
}