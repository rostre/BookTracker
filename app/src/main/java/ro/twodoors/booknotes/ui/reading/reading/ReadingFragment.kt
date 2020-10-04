package ro.twodoors.booknotes.ui.reading.reading

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
import kotlinx.android.synthetic.main.fragment_reading.*
import ro.twodoors.booknotes.R
import ro.twodoors.booknotes.databinding.FragmentReadingBinding
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.ui.ViewModelFactory
import ro.twodoors.booknotes.ui.reading.status.BookStatusAdapter
import ro.twodoors.booknotes.ui.reading.status.ReadingStatusFragmentDirections

class ReadingFragment : Fragment() {

    private lateinit var binding: FragmentReadingBinding
    private val viewModel: ReadingViewModel by lazy {
        val activity =  requireNotNull(this.activity)
        ViewModelProvider(this, ViewModelFactory(activity.application)).get(ReadingViewModel::class.java)
    }

    private val adapter = BookStatusAdapter { view, book -> adapterOnCLick(view, book) }

    private fun adapterOnCLick(view: View, book: Book) {
        when(view.id){
            container.id -> viewModel.onBookClicked(book)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentReadingBinding.inflate(layoutInflater)
        binding.lifecycleOwner = this
        setupRecyclerView()
        subscribeUi()
        return binding.root
    }

    private fun setupRecyclerView() {
        binding.readingBooks.adapter = adapter
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        binding.readingBooks.addItemDecoration(decoration)
    }

    private fun subscribeUi() {
        viewModel.books.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        viewModel.booksCount.observe(viewLifecycleOwner, Observer {
            displayReadingBooksCount(it)
        })

        viewModel.navigateToBookDetail.observe(viewLifecycleOwner, Observer {book ->
            book?.let {
                this.findNavController()
                    .navigate(ReadingStatusFragmentDirections.actionReadingToBookNotesFragment(it.id))
                viewModel.onBookClickedNavigated()
            }
        })
    }

    private fun displayReadingBooksCount(count : Int) {
        when(count){
            0 -> lblReadingBooks.text = resources.getString(R.string.no_reading_books)

            else -> lblReadingBooks.text = "${resources.getString(R.string.reading_books) } ${resources.getQuantityString(R.plurals.books, count, count)}"
        }
    }

}