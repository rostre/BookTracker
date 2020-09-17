package ro.twodoors.booknotes.ui.reading.status

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ro.twodoors.booknotes.databinding.BookStatusItemBinding
import ro.twodoors.booknotes.model.Book

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

    companion object {
        private val BOOK_COMPARATOR = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem == newItem
        }
    }
}

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

            return BooksStatusViewHolder(
                binding
            )
        }
    }

}