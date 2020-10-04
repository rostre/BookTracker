package ro.twodoors.booknotes.ui.wish

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.utils.Utils.Companion.BOOK_COMPARATOR


class WishlistAdapter(val adapterOnClick: (View, Book) -> Unit) : ListAdapter<Book, RecyclerView.ViewHolder>(
    BOOK_COMPARATOR
){

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val bookItem = getItem(position)
        if (bookItem != null){
            (holder as WishlistViewHolder).bindBooks(bookItem, adapterOnClick)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return WishlistViewHolder.create(parent)
    }
}