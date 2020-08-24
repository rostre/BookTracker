package ro.twodoors.booknotes.ui.fragments

import android.os.Bundle
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.chip.Chip
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import ro.twodoors.booknotes.BookAdapter
import ro.twodoors.booknotes.Injection

import ro.twodoors.booknotes.R
import ro.twodoors.booknotes.databinding.FragmentBooksBinding
import ro.twodoors.booknotes.databinding.FragmentSearchBinding
import ro.twodoors.booknotes.model.SearchCriteria
import ro.twodoors.booknotes.ui.BooksLoadStateAdapter
import ro.twodoors.booknotes.ui.SearchViewModel
import java.util.*

private const val SEARCH_BOOKS_BY = "Search books by"

class SearchFragment :  Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var viewModel: SearchViewModel
    private val adapter = BookAdapter()
    private var searchCriteria = SearchCriteria.Keywords

    private var searchJob: Job? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSearchBinding.inflate(layoutInflater)
        val view = binding.root

        viewModel = ViewModelProvider(this, Injection.provideViewModelFactory()).get(SearchViewModel::class.java)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        binding.bookList.addItemDecoration(decoration)

        binding.chipsGroup.setOnCheckedChangeListener { chipGroup, checkedId ->
            val titleOrNull = chipGroup.findViewById<Chip>(checkedId)?.text.toString()
            binding.inputLayout.hint = "$SEARCH_BOOKS_BY ${titleOrNull.toLowerCase()}"
            searchCriteria = SearchCriteria.valueOf(titleOrNull)
        }

        initAdapter()
        initSearch()
        binding.retryButton.setOnClickListener { adapter.retry() }

        return view
    }

    private fun initAdapter() {
        binding.bookList.adapter = adapter.withLoadStateHeaderAndFooter(
            header = BooksLoadStateAdapter { adapter.retry() },
            footer = BooksLoadStateAdapter { adapter.retry() }
        )
        adapter.addLoadStateListener { loadState ->
            // Only show the list if refresh succeeds.
            binding.bookList.isVisible = loadState.source.refresh is LoadState.NotLoading
            // Show loading spinner during initial load or refresh.
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            // Show the retry state if initial load or refresh fails.
            binding.retryButton.isVisible = loadState.source.refresh is LoadState.Error

            // Toast on any error, regardless of whether it came from RemoteMediator or PagingSource
            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.append as? LoadState.Error
                ?: loadState.prepend as? LoadState.Error
            errorState?.let {
                Toast.makeText(
                    activity,
                    "\uD83D\uDE28 Wooops ${it.error}",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    private fun initSearch() {
        binding.searchBook.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_GO) {
                updateBookListFromInput()
                true
            } else {
                false
            }
        }
        binding.searchBook.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                updateBookListFromInput()
                true
            } else {
                false
            }
        }

        // Scroll to top when the list is refreshed from network.
        lifecycleScope.launch {
            adapter.loadStateFlow
                // Only emit when REFRESH LoadState for RemoteMediator changes.
                .distinctUntilChangedBy { it.refresh }
                // Only react to cases where Remote REFRESH completes i.e., NotLoading.
                .filter { it.refresh is LoadState.NotLoading }
                .collect { binding.bookList.scrollToPosition(0) }
        }
    }

    private fun updateBookListFromInput() {
        binding.searchBook.text.trim().let {
            if (it.isNotEmpty()) {
                search(it.toString())
            }
        }
    }

    private fun search(query: String) {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchBooks(query, searchCriteria).collectLatest {
                adapter.submitData(it)
            }
        }
    }

}
