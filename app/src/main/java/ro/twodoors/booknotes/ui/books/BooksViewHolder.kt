package ro.twodoors.booknotes.ui.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import ro.twodoors.booknotes.databinding.MyBooksGridItemBinding
import ro.twodoors.booknotes.databinding.MyBooksItemBinding
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.model.Doc
import ro.twodoors.booknotes.utils.SharedPrefsHelper

class BooksViewHolder
    private constructor(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindBooks(book: Book, adapterOnClick : (View, Book) -> Unit){
        when(SharedPrefsHelper.getItemType(itemView.context)){
            ItemType.GRID.name -> {
                binding as MyBooksGridItemBinding
                binding.apply {
                    this.book = book
                    executePendingBindings()
                }
            }
            else -> {
            binding as MyBooksItemBinding
            binding.apply {
                this.book = book
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
