package ro.twodoors.booknotes.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.ui.addbooknotes.AddBookNotesViewModel
import ro.twodoors.booknotes.ui.bookdetails.BookDetailsViewModel
import ro.twodoors.booknotes.ui.bookinfodetailed.BookInfoDetailedViewModel
import ro.twodoors.booknotes.ui.booknotes.BookNotesViewModel
import ro.twodoors.booknotes.ui.booknotesdetailed.BookNotesDetailedViewModel
import ro.twodoors.booknotes.ui.books.BooksViewModel
import ro.twodoors.booknotes.ui.reading.quitted.QuittedViewModel
import ro.twodoors.booknotes.ui.reading.read.ReadViewModel
import ro.twodoors.booknotes.ui.reading.reading.ReadingViewModel
import ro.twodoors.booknotes.ui.reading.unread.UnreadViewModel
import ro.twodoors.booknotes.ui.search.SearchViewModel
import ro.twodoors.booknotes.ui.wish.WishlistViewModel
import ro.twodoors.booknotes.ui.workdetails.WorkDetailsViewModel

class ViewModelFactory(private val application: Application, private val any: Any? = null) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel() as T
            }
            modelClass.isAssignableFrom(BooksViewModel::class.java) -> {
                BooksViewModel(application) as T
            }
            modelClass.isAssignableFrom(WishlistViewModel::class.java) -> {
                WishlistViewModel(application) as T
            }
            modelClass.isAssignableFrom(WorkDetailsViewModel::class.java) -> {
                WorkDetailsViewModel(application) as T
            }
            modelClass.isAssignableFrom(BookDetailsViewModel::class.java) -> {
                BookDetailsViewModel(application, any as Book) as T
            }
            modelClass.isAssignableFrom(BookNotesViewModel::class.java) -> {
                BookNotesViewModel(application, any as String) as T
            }
            modelClass.isAssignableFrom(AddBookNotesViewModel::class.java) -> {
                AddBookNotesViewModel(application, any as Book) as T
            }
            modelClass.isAssignableFrom(BookNotesDetailedViewModel::class.java) -> {
                BookNotesDetailedViewModel(application, any as String) as T
            }
            modelClass.isAssignableFrom(BookInfoDetailedViewModel::class.java) -> {
                BookInfoDetailedViewModel(application, any as String) as T
            }
            modelClass.isAssignableFrom(QuittedViewModel::class.java) -> {
                QuittedViewModel(application) as T
            }
            modelClass.isAssignableFrom(ReadingViewModel::class.java) -> {
                ReadingViewModel(application) as T
            }
            modelClass.isAssignableFrom(ReadViewModel::class.java) -> {
                ReadViewModel(application) as T
            }
            modelClass.isAssignableFrom(UnreadViewModel::class.java) -> {
                UnreadViewModel(application) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
