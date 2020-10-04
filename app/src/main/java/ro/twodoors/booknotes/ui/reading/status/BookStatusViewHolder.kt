package ro.twodoors.booknotes.ui.reading.status

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ro.twodoors.booknotes.databinding.BookStatusItemBinding
import ro.twodoors.booknotes.model.Book

class BooksStatusViewHolder
private constructor(val binding: BookStatusItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindBooks(book: Book, adapterOnClick : (View, Book) -> Unit){
        binding.apply {
            this.book = book
            this.container.setOnClickListener { adapterOnClick (it, book) }
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup): BooksStatusViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = BookStatusItemBinding.inflate(layoutInflater, parent, false)

            return BooksStatusViewHolder(binding)
        }
    }

}