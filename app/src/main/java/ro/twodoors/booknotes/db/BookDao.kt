package ro.twodoors.booknotes.db

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import ro.twodoors.booknotes.model.Doc
import ro.twodoors.booknotes.model.Category

@Dao
interface BookDao {

    @Query("SELECT * FROM book_table WHERE wishlist = 0 ORDER BY title")
//    @Query("SELECT * FROM book_table ORDER BY title")
    fun getAllBooks() : LiveData<List<Doc>>

    @Query("SELECT * FROM book_table WHERE wishlist = 1 ORDER BY title")
    fun getAllBooksFromWishlist() : LiveData<List<Doc>>

    @Query("SELECT * FROM book_table WHERE id = :id and wishlist = 1")
    fun getBookFromWishlist(id: String) : LiveData<Doc>

    @Query("UPDATE book_table set wishlist = 1 WHERE id = :id")
    fun addBookToWishlist(id: String)

    @Query("UPDATE book_table set wishlist = 0 WHERE id = :id")
    fun removeBookFromWishlist(id: String)

    @Insert(onConflict = REPLACE)
    fun addBook(doc: Doc)

    @Delete
    fun removeBook(doc: Doc)

    //Categories related actions
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: Category)

    @Query("SELECT * FROM category_table ORDER BY name")
    fun getAllCategories() : LiveData<List<Category>>

    @Query("SELECT name FROM category_table ORDER BY name")
    fun getCategoryNames() : LiveData<List<String>>
}