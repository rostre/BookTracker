package ro.twodoors.booktracker.ui

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ro.twodoors.booktracker.data.local.model.Book
import ro.twodoors.booktracker.ui.addbooknotes.AddBookNotesViewModel
import ro.twodoors.booktracker.ui.docdetails.DocDetailsViewModel
import ro.twodoors.booktracker.ui.bookinfo.BookInfoViewModel
import ro.twodoors.booktracker.ui.bookdetails.BookDetailsViewModel
import ro.twodoors.booktracker.ui.booknotes.BookNotesViewModel
import ro.twodoors.booktracker.ui.books.BooksViewModel
import ro.twodoors.booktracker.ui.reading.quitted.QuittedViewModel
import ro.twodoors.booktracker.ui.reading.read.ReadViewModel
import ro.twodoors.booktracker.ui.reading.reading.ReadingViewModel
import ro.twodoors.booktracker.ui.reading.unread.UnreadViewModel
import ro.twodoors.booktracker.ui.search.SearchViewModel
import ro.twodoors.booktracker.ui.wish.WishlistViewModel
import ro.twodoors.booktracker.ui.docs.DocsViewModel

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
            modelClass.isAssignableFrom(DocsViewModel::class.java) -> {
                DocsViewModel(application) as T
            }
            modelClass.isAssignableFrom(DocDetailsViewModel::class.java) -> {
                DocDetailsViewModel(application, any as Book) as T
            }
            modelClass.isAssignableFrom(BookDetailsViewModel::class.java) -> {
                BookDetailsViewModel(application, any as String) as T
            }
            modelClass.isAssignableFrom(AddBookNotesViewModel::class.java) -> {
                AddBookNotesViewModel(application, any as Book) as T
            }
            modelClass.isAssignableFrom(BookNotesViewModel::class.java) -> {
                BookNotesViewModel(application, any as String) as T
            }
            modelClass.isAssignableFrom(BookInfoViewModel::class.java) -> {
                BookInfoViewModel(application, any as String) as T
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
