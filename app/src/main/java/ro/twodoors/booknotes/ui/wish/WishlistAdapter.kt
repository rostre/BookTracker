package ro.twodoors.booknotes.ui.wish

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ro.twodoors.booknotes.model.Doc


class WishlistAdapter(val adapterOnClick: (View, Doc) -> Unit) : ListAdapter<Doc, RecyclerView.ViewHolder>(
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

    companion object {
        private val BOOK_COMPARATOR = object : DiffUtil.ItemCallback<Doc>() {
            override fun areItemsTheSame(oldItem: Doc, newItem: Doc): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Doc, newItem: Doc): Boolean =
                oldItem == newItem
        }
    }
}