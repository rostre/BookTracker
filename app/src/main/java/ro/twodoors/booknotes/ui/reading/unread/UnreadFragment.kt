package ro.twodoors.booknotes.ui.reading.unread

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
import kotlinx.android.synthetic.main.fragment_unread.*
import ro.twodoors.booknotes.R
import ro.twodoors.booknotes.databinding.FragmentUnreadBinding
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.utils.showToast
import ro.twodoors.booknotes.ui.ViewModelFactory
import ro.twodoors.booknotes.ui.reading.status.BookStatusAdapter
import ro.twodoors.booknotes.ui.reading.status.ReadingStatusFragmentDirections

class UnreadFragment : Fragment() {

    private val viewModel: UnreadViewModel by lazy {
        val activity =  requireNotNull(this.activity)
        ViewModelProvider(this, ViewModelFactory(activity.application)).get(UnreadViewModel::class.java)
    }

    private val adapter = BookStatusAdapter { view, book ->
        adapterOnCLick(view, book)
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

        val binding = FragmentUnreadBinding.inflate(layoutInflater)
        
        binding.unreadBooks.adapter = adapter
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        binding.unreadBooks.addItemDecoration(decoration)

        viewModel.books.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        viewModel.booksCount.observe(viewLifecycleOwner, Observer {
            displayUnreadBooksCount(it)
        })

        viewModel.navigateToBookDetail.observe(viewLifecycleOwner, Observer {book ->
            book?.let {
                this.findNavController()
                    .navigate(ReadingStatusFragmentDirections.actionReadingToBookNotesFragment(it.id))
                viewModel.onBookClickedNavigated()
            }
        })

        return binding.root
    }

    private fun displayUnreadBooksCount(count : Int) {
        when(count){
            0 -> lblUnreadBooks.text = resources.getString(R.string.no_unread_books)

            else -> lblUnreadBooks.text = "You have ${resources.getQuantityString(R.plurals.books, count, count)} waiting to be read"
        }
    }

    private fun removeBook(view: View, book: Book) {
        viewModel.removeBook(book)
        this.context?.showToast("Book removed")
    }


}