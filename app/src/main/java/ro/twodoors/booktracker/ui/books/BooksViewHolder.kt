package ro.twodoors.booktracker.ui.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import ro.twodoors.booktracker.databinding.MyBooksGridItemBinding
import ro.twodoors.booktracker.databinding.MyBooksItemBinding
import ro.twodoors.booktracker.data.local.model.Book
import ro.twodoors.booktracker.utils.ItemType
import ro.twodoors.booktracker.utils.SharedPrefsHelper

class BooksViewHolder
    private constructor(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindBooks(book: Book, adapterOnClick : (View, Book) -> Unit){
        when(SharedPrefsHelper.getItemType(itemView.context)){
            ItemType.GRID.name -> {
                binding as MyBooksGridItemBinding
                binding.apply {
                    this.book = book
                    gridContainer.setOnClickListener { adapterOnClick(it, book)}
                    executePendingBindings()
                }
            }
            else -> {
            binding as MyBooksItemBinding
            binding.apply {
                this.book = book
                booksContainer.setOnClickListener { adapterOnClick(it, book)}
                deleteBook.setOnClickListener { adapterOnClick (it, book)}
                executePendingBindings()
            }
        }
        }
    }

    companion object {
        fun create(parent: ViewGroup): BooksViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val type = SharedPrefsHelper.getItemType(parent.context)
            val binding = when(type){
                ItemType.GRID.name -> MyBooksGridItemBinding.inflate(layoutInflater, parent, false)
                else -> MyBooksItemBinding.inflate(layoutInflater, parent, false)
            }

            return BooksViewHolder(binding)
        }
    }


}
