package ro.twodoors.booknotes.ui.books

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ro.twodoors.booknotes.db.BookRepositoryImpl
import ro.twodoors.booknotes.model.Doc


class BooksViewModel(application: Application) : ViewModel() {

    private val bookRepo = BookRepositoryImpl(application)

    val allBooks : LiveData<List<Doc>> = bookRepo.getAllBooks()

    fun removeBook(doc: Doc) = viewModelScope.launch(Dispatchers.IO){
        bookRepo.removeBook(doc)
    }


}