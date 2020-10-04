package ro.twodoors.booknotes.ui.bookinfodetailed

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ro.twodoors.booknotes.db.BookRepositoryImpl
import ro.twodoors.booknotes.model.Book

class BookInfoDetailedViewModel(application: Application, bookId: String) : ViewModel() {

    private val bookRepo = BookRepositoryImpl(application)

    val book: LiveData<Book> = bookRepo.getBook(bookId)
}