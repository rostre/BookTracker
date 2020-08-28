package ro.twodoors.booknotes.ui.search

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import ro.twodoors.booknotes.api.LibraryService
import ro.twodoors.booknotes.data.Repository
import ro.twodoors.booknotes.db.BookRepositoryImpl
import ro.twodoors.booknotes.model.Doc
import ro.twodoors.booknotes.model.SearchCriteria


class SearchViewModel(application: Application) : ViewModel() {

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Doc>>? = null

    fun searchBooks(queryString: String, searchCriteria: SearchCriteria): Flow<PagingData<Doc>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }

        val repository = Repository(LibraryService.create())
        currentQueryValue = queryString
        val newResult: Flow<PagingData<Doc>> = repository.getSearchResultStream(queryString, searchCriteria)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }

    val bookRepo = BookRepositoryImpl(application)

    fun addBook(doc: Doc) = viewModelScope.launch (Dispatchers.IO){
        bookRepo.addBook(doc)
    }

    fun addBookToWishlist(doc: Doc) = viewModelScope.launch (Dispatchers.IO){
        doc.wishlist = true
        bookRepo.addBook(doc)
    }


}