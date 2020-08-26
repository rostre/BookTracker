package ro.twodoors.booknotes.db

import androidx.lifecycle.LiveData
import ro.twodoors.booknotes.model.Doc

interface BookRepository {

    fun getAllBooks() : LiveData<List<Doc>>

    fun getAllBooksFromWishlist() : LiveData<List<Doc>>

    fun getBookFromWishlist(id: String) : LiveData<Doc>

    suspend fun addBookToWishlist(id: String)

    suspend fun removeBookFromWishlist(id: String)

    suspend fun addBook(doc: Doc)

    suspend fun removeBook(doc: Doc)
}