package ro.twodoors.booknotes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import ro.twodoors.booknotes.data.Repository
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.model.SearchCriteria


class SearchViewModel(private val repository: Repository) : ViewModel() {
    private var currentQueryValue: String? = null

    private var currentSearchResult: Flow<PagingData<Book>>? = null

    fun searchBooks(queryString: String, searchCriteria: SearchCriteria): Flow<PagingData<Book>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }
        currentQueryValue = queryString
        val newResult: Flow<PagingData<Book>> = repository.getSearchResultStream(queryString, searchCriteria)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }
}