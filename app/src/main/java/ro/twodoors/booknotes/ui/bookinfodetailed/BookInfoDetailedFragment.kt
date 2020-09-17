package ro.twodoors.booknotes.ui.bookinfodetailed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import ro.twodoors.booknotes.R
import ro.twodoors.booknotes.databinding.FragmentBookInfoDetailedBinding
import ro.twodoors.booknotes.databinding.FragmentBookNotesDetailedBinding
import ro.twodoors.booknotes.model.Book

class BookInfoDetailedFragment(private val book: Book) : Fragment() {

    private lateinit var binding: FragmentBookInfoDetailedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentBookInfoDetailedBinding.inflate(layoutInflater)
        binding.book = book

        return binding.root
    }

}