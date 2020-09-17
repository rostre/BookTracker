package ro.twodoors.booknotes.ui.booknotes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_book_notes.*
import kotlinx.android.synthetic.main.fragment_book_notes_detailed.*
import kotlinx.android.synthetic.main.fragment_reading_status.tabLayout
import kotlinx.android.synthetic.main.fragment_reading_status.toolbar
import kotlinx.android.synthetic.main.fragment_reading_status.viewPager
import ro.twodoors.booknotes.databinding.FragmentBookNotesBinding
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.ui.ViewModelFactory
import ro.twodoors.booknotes.ui.reading.unread.UnreadViewModel
import ro.twodoors.booknotes.utils.initToolbar

class BookNotesFragment : Fragment() {

    private val args: BookNotesFragmentArgs by navArgs()
    private val viewModel : BookNotesViewModel by lazy {
        val activity =  requireNotNull(this.activity)
        ViewModelProvider(this, ViewModelFactory(activity.application, args.bookId)).get(BookNotesViewModel::class.java)
    }
    private lateinit var binding: FragmentBookNotesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBookNotesBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initToolbar(binding.toolbar, true, 0)
        setupAdapter()

        return binding.root
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        setupAdapter()
//    }

    private fun setupAdapter() {
        viewModel.book.observe(viewLifecycleOwner, Observer {
            binding.viewPager.adapter =
                BookNotesPagerAdapter(this.childFragmentManager, it)
        })
        binding.tabLayout.setupWithViewPager(binding.viewPager, true)
    }
}