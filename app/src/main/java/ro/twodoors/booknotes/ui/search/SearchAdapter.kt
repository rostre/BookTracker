package ro.twodoors.booknotes.ui.search

import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ro.twodoors.booknotes.model.Doc

class SearchAdapter(val adapterOnClick: (View, Doc) -> Unit) : PagingDataAdapter<Doc, ViewHolder>(
    BOOK_COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return SearchViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bookItem = getItem(position)
        if (bookItem != null){
            (holder as SearchViewHolder).bindBooks(bookItem, adapterOnClick)
        }
    }

    companion object {
        private val BOOK_COMPARATOR = object : DiffUtil.ItemCallback<Doc>() {
            override fun areItemsTheSame(oldItem: Doc, newItem: Doc): Boolean =
                oldItem.title == newItem.title

            override fun areContentsTheSame(oldItem: Doc, newItem: Doc): Boolean =
                oldItem == newItem
        }
    }
}


