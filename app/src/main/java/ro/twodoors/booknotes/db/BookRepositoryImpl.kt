package ro.twodoors.booknotes.db

import android.app.Application
import androidx.lifecycle.LiveData
import ro.twodoors.booknotes.model.Doc

class BookRepositoryImpl(app: Application) : BookRepository {

    private val bookDao = BookDatabase.getDatabase(app).bookDao()

    override fun getAllBooks(): LiveData<List<Doc>> = bookDao.getAllBooks()

    override fun getAllBooksFromWishlist(): LiveData<List<Doc>> = bookDao.getAllBooksFromWishlist()

    override fun getBookFromWishlist(id: String): LiveData<Doc> = bookDao.getBookFromWishlist(id)

    override suspend fun addBookToWishlist(id: String) = bookDao.addBookToWishlist(id)

    override suspend fun removeBookFromWishlist(id: String) = bookDao.removeBookFromWishlist(id)

    override suspend fun addBook(doc: Doc) = bookDao.addBook(doc)

    override suspend fun removeBook(doc: Doc) = bookDao.removeBook(doc)
}