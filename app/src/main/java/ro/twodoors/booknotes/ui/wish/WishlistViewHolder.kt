package ro.twodoors.booknotes.ui.wish

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ro.twodoors.booknotes.databinding.WishlistItemBinding
import ro.twodoors.booknotes.model.Doc

class WishlistViewHolder private constructor(val binding: WishlistItemBinding) : RecyclerView.ViewHolder(binding.root){

    fun bindBooks(doc: Doc, adapterOnClick : (View, Doc) -> Unit) {
        binding.apply {
            this.doc = doc
            addToBooks.setOnClickListener { adapterOnClick (it, doc)}
            removeFromWish.setOnClickListener { adapterOnClick (it, doc) }
            executePendingBindings()
        }
    }

    companion object {
        fun create(parent: ViewGroup): WishlistViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = WishlistItemBinding.inflate(layoutInflater, parent, false)

            return WishlistViewHolder(binding)
        }
    }
}

