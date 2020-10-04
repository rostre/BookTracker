package ro.twodoors.booknotes.ui.bookinfodetailed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import ro.twodoors.booknotes.R
import ro.twodoors.booknotes.databinding.FragmentBookInfoDetailedBinding
import ro.twodoors.booknotes.databinding.FragmentBookNotesDetailedBinding
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.ui.ViewModelFactory
import ro.twodoors.booknotes.ui.booknotes.BookNotesViewModel

class BookInfoDetailedFragment(private val bookId: String) : Fragment() {

    private val viewModel : BookInfoDetailedViewModel by lazy {
        val activity =  requireNotNull(this.activity)
        ViewModelProvider(this, ViewModelFactory(activity.application, bookId)).get(
            BookInfoDetailedViewModel::class.java)
    }
    private lateinit var binding: FragmentBookInfoDetailedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = FragmentBookInfoDetailedBinding.inflate(layoutInflater)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        return binding.root
    }

}