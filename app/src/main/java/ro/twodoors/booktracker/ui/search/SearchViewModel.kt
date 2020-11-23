package ro.twodoors.booktracker.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import ro.twodoors.booktracker.data.remote.api.LibraryService
import ro.twodoors.booktracker.data.remote.paging.LibraryRepository
import ro.twodoors.booktracker.data.local.model.Doc
import ro.twodoors.booktracker.utils.SearchCriteria


class SearchViewModel : ViewModel() {

    private var currentQueryValue: String? = null
    private var currentSearchResult: Flow<PagingData<Doc>>? = null

    fun searchDocs(queryString: String, searchCriteria: SearchCriteria): Flow<PagingData<Doc>> {
        val lastResult = currentSearchResult
        if (queryString == currentQueryValue && lastResult != null) {
            return lastResult
        }

        val repository =
            LibraryRepository(
                LibraryService.create()
            )
        currentQueryValue = queryString
        val newResult: Flow<PagingData<Doc>> = repository.getSearchResultStream(queryString, searchCriteria)
            .cachedIn(viewModelScope)
        currentSearchResult = newResult
        return newResult
    }


    private val _navigateToDocDetail = MutableLiveData<Doc>()
    val navigateToDocDetail: LiveData<Doc>
        get() = _navigateToDocDetail

    fun onDocClicked(doc: Doc){
        _navigateToDocDetail.value = doc
    }

    fun onDocClickedNavigated(){
        _navigateToDocDetail.value = null
    }


}