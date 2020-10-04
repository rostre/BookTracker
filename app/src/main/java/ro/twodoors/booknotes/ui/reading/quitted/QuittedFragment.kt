package ro.twodoors.booknotes.ui.reading.quitted

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
import kotlinx.android.synthetic.main.fragment_quitted.*
import ro.twodoors.booknotes.R
import ro.twodoors.booknotes.databinding.FragmentQuittedBinding
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.utils.showToast
import ro.twodoors.booknotes.ui.ViewModelFactory
import ro.twodoors.booknotes.ui.reading.status.BookStatusAdapter
import ro.twodoors.booknotes.ui.reading.status.ReadingStatusFragmentDirections


class QuittedFragment : Fragment() {

    private lateinit var binding: FragmentQuittedBinding
    private val viewModel: QuittedViewModel by lazy {
        val activity =  requireNotNull(this.activity)
        ViewModelProvider(this, ViewModelFactory(activity.application)).get(QuittedViewModel::class.java)
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

        binding = FragmentQuittedBinding.inflate(layoutInflater)

        setupRecyclerView()
        subscribeUi()
        return binding.root
    }


    private fun setupRecyclerView(){
        binding.quittedBooks.adapter = adapter
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        binding.quittedBooks.addItemDecoration(decoration)
    }

    private fun subscribeUi() {
        viewModel.books.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        viewModel.booksCount.observe(viewLifecycleOwner, Observer {
            displayQuittedBooksCount(it)
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

    private fun displayQuittedBooksCount(count : Int) {
        when(count){
            0 -> lblQuittedBooks.text = resources.getString(R.string.no_quitted_books)

            else -> lblQuittedBooks.text = "${resources.getString(R.string.quitted_books) } ${resources.getQuantityString(
                R.plurals.books, count, count)}"
        }
    }
}