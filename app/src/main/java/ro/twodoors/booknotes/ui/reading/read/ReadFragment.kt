package ro.twodoors.booknotes.ui.reading.read

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.android.synthetic.main.book_status_item.*
import kotlinx.android.synthetic.main.fragment_read.*
import kotlinx.android.synthetic.main.fragment_reading.*
import ro.twodoors.booknotes.R
import ro.twodoors.booknotes.databinding.FragmentReadBinding
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.utils.showToast
import ro.twodoors.booknotes.ui.ViewModelFactory
import ro.twodoors.booknotes.ui.reading.status.BookStatusAdapter
import ro.twodoors.booknotes.ui.reading.status.ReadingStatusFragmentDirections


class ReadFragment : Fragment() {

    private lateinit var binding: FragmentReadBinding
    private val viewModel: ReadViewModel by lazy {
        val activity =  requireNotNull(this.activity)
        ViewModelProvider(this, ViewModelFactory(activity.application)).get(ReadViewModel::class.java)
    }

    private val adapter = BookStatusAdapter { view, book -> adapterOnCLick(view, book)
    }

    private fun adapterOnCLick(view: View, book: Book) {
        when(view.id){
            container.id -> viewModel.onBookClicked(book)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentReadBinding.inflate(layoutInflater)

        setupRecyclerView()
        subscribeUi()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.readBooks.adapter = adapter
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        binding.readBooks.addItemDecoration(decoration)
    }

    private fun subscribeUi() {
        viewModel.books.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        viewModel.booksCount.observe(viewLifecycleOwner, Observer {
            displayReadBooksCount(it)
        })

        viewModel.navigateToBookDetail.observe(viewLifecycleOwner, Observer {book ->
            book?.let {
                this.findNavController()
                    .navigate(ReadingStatusFragmentDirections.actionReadingToBookNotesFragment(it.id))
                viewModel.onBookClickedNavigated()
            }
        })
    }

    private fun removeBook(view: View, book: Book) {
        viewModel.removeBook(book)
        this.context?.showToast("Book removed")
    }

    private fun displayReadBooksCount(count : Int) {
        when(count){
            0 -> lblReadBooks.text = resources.getString(R.string.no_read_books)

            else -> lblReadBooks.text = "${resources.getString(R.string.read_books) } ${resources.getQuantityString(
                    R.plurals.books, count, count)}"

        }
    }

}