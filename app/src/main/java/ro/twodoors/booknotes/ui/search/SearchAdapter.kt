package ro.twodoors.booknotes.ui.search

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import ro.twodoors.booknotes.model.Doc

class SearchAdapter(val clickListener: OnClickListener) : PagingDataAdapter<Doc, ViewHolder>(
    DOC_COMPARATOR
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return SearchViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val bookItem = getItem(position)
        if (bookItem != null){
            (holder as SearchViewHolder).bindBooks(bookItem, clickListener)
        }
    }

    companion object {
        private val DOC_COMPARATOR = object : DiffUtil.ItemCallback<Doc>() {
            override fun areItemsTheSame(oldItem: Doc, newItem: Doc): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Doc, newItem: Doc): Boolean =
                oldItem == newItem
        }
    }
}

class OnClickListener(val clickListener: (entity: Any ) -> Unit){
    fun onClick(entity: Any) = clickListener(entity)
}


