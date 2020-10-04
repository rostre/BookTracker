package ro.twodoors.booknotes.ui.bookdetails

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ro.twodoors.booknotes.db.BookRepositoryImpl
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.utils.ReadingStatus
import ro.twodoors.booknotes.utils.getCurrentDateTime

class BookDetailsViewModel(application: Application, selectedBook: Book) : ViewModel() {

    val bookRepo = BookRepositoryImpl(application)

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