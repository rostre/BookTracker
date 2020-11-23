package ro.twodoors.booktracker.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.RecyclerView
import ro.twodoors.booktracker.R
import ro.twodoors.booktracker.databinding.BooksLoadStateFooterViewItemBinding

class BooksLoadStateViewHolder (
    private val binding: BooksLoadStateFooterViewItemBinding,
    retry: () -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    init {
        binding.retryButton.setOnClickListener { retry.invoke() }
    }

    fun bind(loadState: LoadState) {
        if (loadState is LoadState.Error) {
            binding.errorMsg.text = loadState.error.localizedMessage
        }
        binding.progressBar.isVisible = loadState is LoadState.Loading
        binding.retryButton.isVisible = loadState !is LoadState.Loading
        binding.errorMsg.isVisible = loadState !is LoadState.Loading
    }

    companion object {
        fun create(parent: ViewGroup, retry: () -> Unit): BooksLoadStateViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.books_load_state_footer_view_item, parent, false)
            val binding = BooksLoadStateFooterViewItemBinding.bind(view)
            return BooksLoadStateViewHolder(binding, retry)
        }
    }
}