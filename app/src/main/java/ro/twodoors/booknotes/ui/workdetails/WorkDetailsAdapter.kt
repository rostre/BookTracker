package ro.twodoors.booknotes.ui.workdetails

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.model.Doc
import ro.twodoors.booknotes.ui.search.OnClickListener
import ro.twodoors.booknotes.ui.search.SearchViewHolder


class WorkDetailsAdapter (val clickListener: OnClickListener) : PagingDataAdapter<Book, RecyclerView.ViewHolder>(
    BOOK_COMPARATOR_URL
) {

    private var doc: Doc? = null

    fun setupDoc(doc: Doc){
        this.doc = doc
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WorkDetailsViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val bookItem = getItem(position)
        val isbn = doc?.isbn?.get(position) ?: ""
        if (bookItem != null){
            (holder as WorkDetailsViewHolder).bindBooks(bookItem, isbn, clickListener)
        }
    }

    companion object {
        private val BOOK_COMPARATOR_URL = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem.url == newItem.url

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem == newItem
        }
    }
}

