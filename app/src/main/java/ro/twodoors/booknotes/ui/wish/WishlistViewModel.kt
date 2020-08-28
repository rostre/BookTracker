package ro.twodoors.booknotes.ui.wish

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ro.twodoors.booknotes.db.BookRepositoryImpl
import ro.twodoors.booknotes.model.Doc

class WishlistViewModel (application: Application) : ViewModel() {

    private val bookRepo = BookRepositoryImpl(application)

    val allBooksFromWishlist : LiveData<List<Doc>> = bookRepo.getAllBooksFromWishlist()

    fun addToBooks(doc: Doc) = viewModelScope.launch(Dispatchers.IO){
        bookRepo.removeBookFromWishlist(doc.id)
    }

    fun removeBookFromWishlist(doc: Doc) = viewModelScope.launch(Dispatchers.IO){
        bookRepo.removeBook(doc)
    }
}