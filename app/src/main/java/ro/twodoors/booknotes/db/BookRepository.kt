package ro.twodoors.booknotes.db

import androidx.lifecycle.LiveData
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.ui.reading.status.ReadingStatus

interface BookRepository {

    fun getAllBooks() : LiveData<List<Book>>

    fun getAllBooksFromWishlist() : LiveData<List<Book>>

    fun getBookFromWishlist(id: String) : LiveData<Book>

    fun getBook(id: String) : LiveData<Book>

    fun getBookByStatus(readingStatus: ReadingStatus) : LiveData<List<Book>>

    fun isWishlisted(id: String) : LiveData<Boolean>

    fun isAlreadySaved(id: String) : LiveData<Book>

    suspend fun addBookToWishlist(id: String)

    suspend fun removeBookFromWishlist(id: String)

    suspend fun addBook(book: Book)

    suspend fun updateBook(book: Book)

    suspend fun removeBook(book: Book)
}