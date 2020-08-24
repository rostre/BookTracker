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
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import ro.twodoors.booknotes.BookAdapter
import ro.twodoors.booknotes.Injection
import ro.twodoors.booknotes.R
import ro.twodoors.booknotes.databinding.FragmentBooksBinding
import ro.twodoors.booknotes.model.SearchCriteria
import ro.twodoors.booknotes.ui.BooksLoadStateAdapter
import ro.twodoors.booknotes.ui.SearchViewModel

/**
 * A simple [Fragment] subclass.
 */

class BooksFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_books, container, false)
    }


    companion object {
        private const val LAST_SEARCH_QUERY: String = "last_search_query"
        private const val DEFAULT_QUERY = "Master the game"
    }

}
