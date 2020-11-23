package ro.twodoors.booktracker.ui.reading.read

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import ro.twodoors.booktracker.data.local.db.BookRepositoryImpl
import ro.twodoors.booktracker.data.local.model.Book
import ro.twodoors.booktracker.utils.ReadingStatus

class ReadViewModel(application: Application) : ViewModel() {

    private val bookRepo =
        BookRepositoryImpl(application)

    val books : LiveData<List<Book>> = bookRepo.getBooksByStatus(ReadingStatus.Read)

    val booksCount : LiveData<Int> = bookRepo.getBooksCountByStatus(ReadingStatus.Read)

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