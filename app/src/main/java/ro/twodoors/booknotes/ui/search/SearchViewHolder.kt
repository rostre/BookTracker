package ro.twodoors.booknotes.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ro.twodoors.booknotes.databinding.SearchItemBinding
import ro.twodoors.booknotes.model.Doc

class SearchViewHolder private constructor(val binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bindBooks(doc: Doc, adapterOnClick : (View, Doc) -> Unit) {
        binding.apply {
            this.doc = doc
            addBook.setOnClickListener { adapterOnClick (it, doc)}
            addToWishlist.setOnClickListener { adapterOnClick (it, doc) }
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup): SearchViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = SearchItemBinding.inflate(layoutInflater, parent, false)

            return SearchViewHolder(binding)
        }
    }
}