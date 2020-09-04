package ro.twodoors.booknotes.ui.wish

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ro.twodoors.booknotes.db.BookRepositoryImpl
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.model.Doc

class WishlistViewModel (application: Application) : ViewModel() {

    private val bookRepo = BookRepositoryImpl(application)

    val allBooksFromWishlist : LiveData<List<Book>> = bookRepo.getAllBooksFromWishlist()

    fun addToBooks(book: Book) = viewModelScope.launch(Dispatchers.IO){
        bookRepo.removeBookFromWishlist(book.id)
    }

    fun removeBookFromWishlist(book: Book) = viewModelScope.launch(Dispatchers.IO){
        bookRepo.removeBook(book)
    }
}