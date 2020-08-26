package ro.twodoors.booknotes.ui.books

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.fragment_books.*
import ro.twodoors.booknotes.api.LibraryService
import ro.twodoors.booknotes.data.Repository
import ro.twodoors.booknotes.databinding.FragmentBooksBinding
import ro.twodoors.booknotes.model.Doc
import ro.twodoors.booknotes.showToast
import ro.twodoors.booknotes.ui.ViewModelFactory

class BooksFragment : Fragment() {


    private lateinit var binding: FragmentBooksBinding

    private val viewModel: BooksViewModel by lazy {
        val activity =  requireNotNull(this.activity){ "You can only access the viewModel after onActivityCreated()" }
        val viewModelFactory = ViewModelFactory(Repository(LibraryService.create()), activity.application)
        ViewModelProvider(this, viewModelFactory).get(BooksViewModel::class.java)
    }
    private val adapter = BookAdapter { removeBook(it) }

    private fun removeBook(doc: Doc) {
        viewModel.removeBook(doc)
        this.context?.showToast("Book removed")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentBooksBinding.inflate(layoutInflater)
        binding.myBooks.adapter = adapter

        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        binding.myBooks.addItemDecoration(decoration)

        viewModel.allBooks.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        return binding.root
    }



}
