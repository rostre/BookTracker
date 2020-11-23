package ro.twodoors.booktracker.ui.docs

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ro.twodoors.booktracker.databinding.BookItemBinding
import ro.twodoors.booktracker.data.local.model.Book
import ro.twodoors.booktracker.ui.search.OnClickListener


class DocsViewHolder private constructor(val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root){

    fun bindBooks(book: Book, isbn : String, clickListener: OnClickListener) {
        binding.apply {
            this.book = book
            this.isbn = isbn
            this.clickListener = clickListener
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup): DocsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = BookItemBinding.inflate(layoutInflater, parent, false)

            return DocsViewHolder(binding)
        }
    }
}

