package ro.twodoors.booknotes.ui.reading.read

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ro.twodoors.booknotes.db.BookRepositoryImpl
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.utils.ReadingStatus

class ReadViewModel(application: Application) : ViewModel() {

    private val bookRepo = BookRepositoryImpl(application)

    val books : LiveData<List<Book>> = bookRepo.getBooksByStatus(ReadingStatus.Read)

    val booksCount : LiveData<Int> = bookRepo.getBooksCountByStatus(ReadingStatus.Read)

    fun removeBook(book: Book) = viewModelScope.launch(Dispatchers.IO){
        bookRepo.removeBook(book)
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