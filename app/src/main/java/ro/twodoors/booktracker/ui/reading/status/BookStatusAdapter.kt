package ro.twodoors.booktracker.ui.reading.status

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ro.twodoors.booktracker.data.local.model.Book
import ro.twodoors.booktracker.utils.Utils.Companion.BOOK_COMPARATOR

class BookStatusAdapter(
    val adapterOnClick: (View, Book) -> Unit)
    : ListAdapter<Book, RecyclerView.ViewHolder>(BOOK_COMPARATOR) {

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val bookItem = getItem(position)
        if (bookItem != null){
            (holder as BooksStatusViewHolder).bindBooks(bookItem, adapterOnClick)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BooksStatusViewHolder.create(
            parent
        )
    }
}