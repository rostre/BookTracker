package ro.twodoors.booknotes.ui.workdetails

import android.app.Application
import android.os.Bundle
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import ro.twodoors.booknotes.api.LibraryService
import ro.twodoors.booknotes.data.Repository
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.model.Doc

class WorkDetailsViewModel(app: Application) : AndroidViewModel(app) {

    fun searchBooks(doc: Doc) : Flow<PagingData<Book>> {
        val repository = Repository(LibraryService.create())
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