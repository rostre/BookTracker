package ro.twodoors.booktracker.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ro.twodoors.booktracker.databinding.SearchItemBinding
import ro.twodoors.booktracker.data.local.model.Doc

class SearchViewHolder private constructor(val binding: SearchItemBinding) : RecyclerView.ViewHolder(binding.root) {

    //fun bindBooks(doc: Doc, adapterOnClick : (View, Doc) -> Unit) {
    fun bindBooks(doc: Doc, clickListener: OnClickListener) {
        binding.apply {
            this.doc = doc
            this.clickListener = clickListener
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