package ro.twodoors.booknotes.ui.workdetails

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ro.twodoors.booknotes.databinding.BookItemBinding
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.ui.search.OnClickListener


class WorkDetailsViewHolder private constructor(val binding: BookItemBinding) : RecyclerView.ViewHolder(binding.root){

    fun bindBooks(book: Book, isbn : String, clickListener: OnClickListener) {
        binding.apply {
            this.book = book
            this.isbn = isbn
            this.clickListener = clickListener
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup): WorkDetailsViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = BookItemBinding.inflate(layoutInflater, parent, false)

            return WorkDetailsViewHolder(binding)
        }
    }
}

