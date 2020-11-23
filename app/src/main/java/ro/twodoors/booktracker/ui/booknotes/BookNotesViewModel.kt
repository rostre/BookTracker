package ro.twodoors.booktracker.ui.booknotes

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ro.twodoors.booktracker.data.local.db.BookRepositoryImpl
import ro.twodoors.booktracker.data.local.model.Book

class BookNotesViewModel(application: Application, bookId: String) : ViewModel() {

    private val bookRepo =
        BookRepositoryImpl(application)

    val book: LiveData<Book> = bookRepo.getBook(bookId)

    fun updateBook() = viewModelScope.launch(Dispatchers.IO){
        bookRepo.updateBook(book.value!!)
    }

    private val _navigateToNotesDetail = MutableLiveData<Book>()
    val navigateToNotesDetail: LiveData<Book>
        get() = _navigateToNotesDetail

    fun onNotesClicked(){
        _navigateToNotesDetail.value = book.value
    }

    fun onNotesClickedNavigated(){
        _navigateToNotesDetail.value = null
    }
}