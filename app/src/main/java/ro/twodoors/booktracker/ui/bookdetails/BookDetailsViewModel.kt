package ro.twodoors.booktracker.ui.bookdetails

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import ro.twodoors.booktracker.data.local.db.BookRepositoryImpl
import ro.twodoors.booktracker.data.local.model.Book

class BookDetailsViewModel(application: Application, bookId: String) : ViewModel() {

    private val bookRepo =
        BookRepositoryImpl(application)

    val book : LiveData<Book> = bookRepo.getBook(bookId)
}