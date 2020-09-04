package ro.twodoors.booknotes.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.model.Category
import ro.twodoors.booknotes.model.Converter

@Database(entities = [Book::class, Category::class], version = 3, exportSchema = false)
@TypeConverters(Converter::class)
abstract class BookDatabase : RoomDatabase() {

    abstract fun bookDao() : BookDao

    companion object {

        @Volatile
        private var INSTANCE: BookDatabase? = null

        fun getDatabase(context: Context) : BookDatabase{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        BookDatabase::class.java,
                        "book_database")
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}