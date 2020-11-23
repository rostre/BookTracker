package ro.twodoors.booktracker.data.local.db

import android.app.Application
import androidx.lifecycle.LiveData
import ro.twodoors.booktracker.data.local.model.Book
import ro.twodoors.booktracker.utils.ReadingStatus

class BookRepositoryImpl(app: Application) :
    BookRepository {

    private val bookDao = BookDatabase.getDatabase(app).bookDao()

    override fun getAllBooks(): LiveData<List<Book>> = bookDao.getAllBooks()

    override fun getAllBooksFromWishlist(): LiveData<List<Book>> = bookDao.getAllBooksFromWishlist()

    override fun getBookFromWishlist(id: String): LiveData<Book> = bookDao.getBookFromWishlist(id)

    override fun getBook(id: String): LiveData<Book> = bookDao.getBook(id)

    override fun getBooksByStatus(readingStatus: ReadingStatus): LiveData<List<Book>> = bookDao.getBooksByStatus(readingStatus)

    override fun getBooksCountByStatus(readingStatus: ReadingStatus): LiveData<Int> = bookDao.getBooksCountByStatus(readingStatus)

    override fun isWishlisted(id: String): LiveData<Boolean> = bookDao.isWishlisted(id)

    override fun isAlreadySaved(id: String): LiveData<Book> = bookDao.isAlreadySaved(id)

    override suspend fun addBookToWishlist(id: String) = bookDao.addBookToWishlist(id)

    override suspend fun removeBookFromWishlist(id: String) = bookDao.removeBookFromWishlist(id)

    override suspend fun addBook(book: Book) = bookDao.addBook(book)

    override suspend fun updateBook(book: Book) = bookDao.updateBook(book)

    override suspend fun removeBook(book: Book) = bookDao.removeBook(book)
}