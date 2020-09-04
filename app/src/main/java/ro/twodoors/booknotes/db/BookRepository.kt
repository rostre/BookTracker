package ro.twodoors.booknotes.db

import androidx.lifecycle.LiveData
import ro.twodoors.booknotes.model.Book

interface BookRepository {

    fun getAllBooks() : LiveData<List<Book>>

    fun getAllBooksFromWishlist() : LiveData<List<Book>>

    fun getBookFromWishlist(id: String) : LiveData<Book>

    fun getBook(id: String) : LiveData<Book>

    fun isWishlisted(id: String) : LiveData<Boolean>

    suspend fun addBookToWishlist(id: String)

    suspend fun removeBookFromWishlist(id: String)

    suspend fun addBook(book: Book)

    suspend fun removeBook(book: Book)
}