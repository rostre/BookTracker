package ro.twodoors.booktracker.ui.docdetails

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ro.twodoors.booktracker.data.local.db.BookRepositoryImpl
import ro.twodoors.booktracker.data.local.model.Book
import ro.twodoors.booktracker.utils.ReadingStatus
import ro.twodoors.booktracker.utils.getCurrentDateTime

class DocDetailsViewModel(application: Application, selectedBook: Book) : ViewModel() {

    val bookRepo =
        BookRepositoryImpl(application)

    val isFavorite : LiveData<Boolean> = bookRepo.isWishlisted(selectedBook.id)

    val isAlreadySaved = bookRepo.isAlreadySaved(selectedBook.id)

    fun addBook(book: Book) = viewModelScope.launch (Dispatchers.IO){
        book.wishlist = false
        book.dateAdded = getCurrentDateTime()
        book.readingStatus = ReadingStatus.Unread
        bookRepo.addBook(book)
    }

    fun addBookToWishlist(book: Book) = viewModelScope.launch (Dispatchers.IO){
        book.wishlist = true
        book.dateAdded = getCurrentDateTime()
        bookRepo.addBook(book)
    }

    fun removeBookFromWishlist(book: Book) = viewModelScope.launch(Dispatchers.IO){
        bookRepo.removeBook(book)
    }

    fun removeBook(book: Book) = viewModelScope.launch(Dispatchers.IO){
        bookRepo.removeBook(book)
    }


}