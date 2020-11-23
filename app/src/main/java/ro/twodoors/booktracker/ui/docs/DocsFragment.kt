package ro.twodoors.booktracker.ui.docs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ro.twodoors.booktracker.data.local.model.Book
import ro.twodoors.booktracker.data.local.model.Doc
import ro.twodoors.booktracker.databinding.FragmentDocsBinding
import ro.twodoors.booktracker.ui.BooksLoadStateAdapter
import ro.twodoors.booktracker.ui.ViewModelFactory
import ro.twodoors.booktracker.ui.search.OnClickListener
import ro.twodoors.booktracker.utils.initToolbar


class DocsFragment : Fragment() {

    private lateinit var binding: FragmentDocsBinding
    private val viewModel: DocsViewModel by lazy {
        val activity =  requireNotNull(this.activity)
        ViewModelProvider(this, ViewModelFactory(activity.application)).get(DocsViewModel::class.java)
    }
    private val adapter = DocsAdapter(OnClickListener{ book -> viewModel.onBookClicked(book as Book) })
    private var searchJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        binding = FragmentDocsBinding.inflate(layoutInflater)
        val doc = DocsFragmentArgs.fromBundle(requireArguments()).doc
        binding.doc = doc
        adapter.setupDoc(doc)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        initToolbar(binding.toolbar, true)
        val decoration = DividerItemDecoration(activity, DividerItemDecoration.VERTICAL)
        binding.books.addItemDecoration(decoration)
        binding.retryButton.setOnClickListener { adapter.retry() }

        initAdapter()
        populateBooks(doc)

        viewModel.navigateToBookDetail.observe(viewLifecycleOwner, Observer {book ->
            book?.let {
                this.findNavController()
                    .navigate(DocsFragmentDirections.actionWorkDetailsFragmentToBookDetailsFragment(it))
                viewModel.onBookClickedNavigated()
            }
        })

        return binding.root
    }

    private fun initAdapter() {
        binding.books.adapter = adapter.withLoadStateHeaderAndFooter(
            header = BooksLoadStateAdapter { adapter.retry() },
            footer = BooksLoadStateAdapter { adapter.retry() }
        )
        adapter.addLoadStateListener { loadState ->
            // Only show the list if refresh succeeds.
            binding.books.isVisible = loadState.source.refresh is LoadState.NotLoading
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

    private fun populateBooks(doc: Doc) {
        // Make sure we cancel the previous job before creating a new one
        searchJob?.cancel()
        searchJob = lifecycleScope.launch {
            viewModel.searchBooks(doc).collectLatest {
                adapter.submitData(it)
            }
        }
    }
}