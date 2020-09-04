package ro.twodoors.booknotes.ui.bookdetails

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ro.twodoors.booknotes.db.BookRepositoryImpl
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.model.Doc

class BookDetailsViewModel(application: Application) : ViewModel() {

    val bookRepo = BookRepositoryImpl(application)

    fun addBook(book: Book) = viewModelScope.launch (Dispatchers.IO){
        book.wishlist = false
        bookRepo.addBook(book)
    }

    fun addBookToWishlist(book: Book) = viewModelScope.launch (Dispatchers.IO){
        book.wishlist = true
        bookRepo.addBook(book)
    }



}