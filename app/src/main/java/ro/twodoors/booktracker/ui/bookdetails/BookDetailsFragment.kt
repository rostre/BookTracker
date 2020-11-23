package ro.twodoors.booktracker.ui.bookdetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import ro.twodoors.booktracker.databinding.FragmentBookDetailsBinding
import ro.twodoors.booktracker.ui.ViewModelFactory
import ro.twodoors.booktracker.utils.initToolbar

class BookDetailsFragment : Fragment() {

    private val args: BookDetailsFragmentArgs by navArgs()
    private val viewModel : BookDetailsViewModel by lazy {
        val activity =  requireNotNull(this.activity)
        ViewModelProvider(this, ViewModelFactory(activity.application, args.bookId)).get(BookDetailsViewModel::class.java)
    }
    private lateinit var binding: FragmentBookDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBookDetailsBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initToolbar(binding.toolbar, true, 0)
        setupAdapter()

        return binding.root
    }


    private fun setupAdapter() {
        viewModel.book.observe(viewLifecycleOwner, Observer {
            binding.viewPager.adapter =
                BookNotesPagerAdapter(this.childFragmentManager, it.id)
        })
        binding.tabLayout.setupWithViewPager(binding.viewPager, true)
    }
}