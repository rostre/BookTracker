package ro.twodoors.booknotes.ui.addbooknotes

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ro.twodoors.booknotes.db.BookRepositoryImpl
import ro.twodoors.booknotes.model.Book

class AddBookNotesViewModel(application: Application, val book: Book) : ViewModel() {

    private val bookRepo = BookRepositoryImpl(application)

    val noteValue = MutableLiveData<String>()

    init {
        noteValue.value = book.bookNotes
    }

    fun saveNote() : String? {
        val note = noteValue.value
        if (note.isNullOrEmpty())
            return "Note cannot be empty"
        book.bookNotes = note

        viewModelScope.launch(Dispatchers.IO) {
            bookRepo.updateBook(book)
        }
        return null
    }
}