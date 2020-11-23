package ro.twodoors.booktracker.ui.wish

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ro.twodoors.booktracker.data.local.db.BookRepositoryImpl
import ro.twodoors.booktracker.data.local.model.Book
import ro.twodoors.booktracker.utils.ReadingStatus
import ro.twodoors.booktracker.utils.getCurrentDateTime

class WishlistViewModel (application: Application) : ViewModel() {

    private val bookRepo =
        BookRepositoryImpl(application)

    val allBooksFromWishlist : LiveData<List<Book>> = bookRepo.getAllBooksFromWishlist()

    fun addToBooks(book: Book) = viewModelScope.launch(Dispatchers.IO){
        book.wishlist = false
        book.dateAdded = getCurrentDateTime()
        book.readingStatus = ReadingStatus.Unread
        bookRepo.updateBook(book)
    }

    fun removeBookFromWishlist(book: Book) = viewModelScope.launch(Dispatchers.IO){
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