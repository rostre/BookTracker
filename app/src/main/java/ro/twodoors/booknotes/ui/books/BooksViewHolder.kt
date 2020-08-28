package ro.twodoors.booknotes.ui.books

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ro.twodoors.booknotes.databinding.MyBooksItemBinding
import ro.twodoors.booknotes.model.Doc

class BooksViewHolder
    private constructor(val binding: MyBooksItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindBooks(doc: Doc, adapterOnClick : (View, Doc) -> Unit){
        binding.apply {
            this.doc = doc
            deleteBook.setOnClickListener { adapterOnClick (it, doc)}
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