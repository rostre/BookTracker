package ro.twodoors.booknotes.ui.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ro.twodoors.booknotes.databinding.MyBooksItemBinding
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.model.Doc

class BooksViewHolder
    private constructor(val binding: MyBooksItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindBooks(book: Book, adapterOnClick : (View, Book) -> Unit){
        binding.apply {
            this.book = book
            deleteBook.setOnClickListener { adapterOnClick (it, book)}
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup): BooksViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = MyBooksItemBinding.inflate(layoutInflater, parent, false)

            return BooksViewHolder(binding)
        }
    }

}