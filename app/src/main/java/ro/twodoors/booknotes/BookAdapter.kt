package ro.twodoors.booknotes

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.ui.BookViewHolder

class BookAdapter : PagingDataAdapter<Book, ViewHolder>(BOOK_COMPARATOR) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return BookViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bookItem = getItem(position)
        if (bookItem != null){
            (holder as BookViewHolder).bindBooks(bookItem)
        }
    }

    companion object {
        private val BOOK_COMPARATOR = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem == newItem
        }
    }
}

