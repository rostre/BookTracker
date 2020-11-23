package ro.twodoors.booktracker.ui.docs

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import ro.twodoors.booktracker.data.remote.api.LibraryService
import ro.twodoors.booktracker.data.remote.paging.LibraryRepository
import ro.twodoors.booktracker.data.local.model.Book
import ro.twodoors.booktracker.data.local.model.Doc

class DocsViewModel(app: Application) : AndroidViewModel(app) {

    fun searchBooks(doc: Doc) : Flow<PagingData<Book>> {
        val repository =
            LibraryRepository(
                LibraryService.create()
            )
        return repository.getBooksResultStream(doc).cachedIn(viewModelScope)
    }

    private val _navigateToBookDetail = MutableLiveData<Book>()
    val navigateToBookDetail: LiveData<Book>
        get() = _navigateToBookDetail

    fun onBookClicked(book: Book){
        _navigateToBookDetail.value = book
    }

    fun onBookClickedNavigated(){
        _navigateToBookDetail.value = null
    }
}