package ro.twodoors.booknotes.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.model.Category
import ro.twodoors.booknotes.utils.ReadingStatus

@Dao
interface BookDao {

    @Query("SELECT * FROM book_table WHERE wishlist = 0 ORDER BY dateAdded DESC")
    fun getAllBooks() : LiveData<List<Book>>

    @Query("SELECT * FROM book_table WHERE wishlist = 1 ORDER BY dateAdded DESC")
    fun getAllBooksFromWishlist() : LiveData<List<Book>>

    @Query("SELECT * FROM book_table WHERE id = :id and wishlist = 1")
    fun getBookFromWishlist(id: String) : LiveData<Book>

    @Query("SELECT * FROM book_table WHERE id = :id")
    fun getBook(id: String) : LiveData<Book>

    @Query("SELECT * FROM book_table WHERE readingStatus = :status ORDER BY dateAdded DESC")
    fun getBooksByStatus(status: ReadingStatus) : LiveData<List<Book>>

    @Query("SELECT COUNT(*) FROM book_table WHERE readingStatus = :status")
    fun getBooksCountByStatus(status: ReadingStatus) : LiveData<Int>

    @Query("SELECT wishlist FROM book_table WHERE id = :id")
    fun isWishlisted(id: String) : LiveData<Boolean>

    @Query("SELECT * FROM book_table WHERE id = :id AND wishlist = 0")
    fun isAlreadySaved(id: String) : LiveData<Book>

    @Query("UPDATE book_table set wishlist = 1 WHERE id = :id")
    fun addBookToWishlist(id: String)

    @Query("UPDATE book_table set wishlist = 0 WHERE id = :id")
    fun removeBookFromWishlist(id: String)

    @Insert(onConflict = REPLACE)
    fun addBook(book: Book)

    @Update
    fun updateBook(book: Book)

    @Delete
    fun removeBook(book: Book)

    //Categories related actions
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: Category)

    @Query("SELECT * FROM category_table ORDER BY name")
    fun getAllCategories() : LiveData<List<Category>>

    @Query("SELECT name FROM category_table ORDER BY name")
    fun getCategoryNames() : LiveData<List<String>>
}