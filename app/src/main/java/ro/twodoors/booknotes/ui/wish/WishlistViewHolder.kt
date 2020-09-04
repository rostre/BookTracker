package ro.twodoors.booknotes.ui.wish

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ro.twodoors.booknotes.databinding.WishlistItemBinding
import ro.twodoors.booknotes.model.Book

class WishlistViewHolder private constructor(val binding: WishlistItemBinding) : RecyclerView.ViewHolder(binding.root){

    fun bindBooks(book: Book, adapterOnClick : (View, Book) -> Unit) {
        binding.apply {
            this.book = book
            addToBooks.setOnClickListener { adapterOnClick (it, book)}
            removeFromWish.setOnClickListener { adapterOnClick (it, book) }
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup): WishlistViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = WishlistItemBinding.inflate(layoutInflater, parent, false)

            return WishlistViewHolder(binding)
        }
    }
}

