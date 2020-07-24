package ro.twodoors.booknotes.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.model.Category

@Dao
interface BookDao {

    @Query("SELECT * FROM book_table WHERE wishlist = 0 ORDER BY title")
    fun getAllBooks() : LiveData<List<Book>>

    @Query("SELECT * FROM book_table WHERE wishlist = 1 ORDER BY title")
    fun getBooksInWishlist() : LiveData<List<Book>>

    //Categories related actions
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: Category)

    @Query("SELECT * FROM category_table ORDER BY name")
    fun getAllCategories() : LiveData<List<Category>>

    @Query("SELECT name FROM category_table ORDER BY name")
    fun getCategoryNames() : LiveData<List<String>>
}