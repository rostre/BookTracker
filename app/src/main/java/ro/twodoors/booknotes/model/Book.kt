package ro.twodoors.booknotes.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import ro.twodoors.booknotes.utils.ReadingStatus
import java.io.Serializable

@Entity(tableName = "book_table",
    foreignKeys = [ForeignKey(entity = Category::class, parentColumns = ["name"], childColumns = ["category"], onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)])
data class Book(
    @PrimaryKey
    @SerializedName("key") val id: String,
    @SerializedName("authors") val authors: List<Author>?,
    @SerializedName("category") var category: String?,
    @SerializedName("cover") val cover: Cover?,
    @SerializedName("date_added") var dateAdded: Long?,
    @SerializedName("description") val description: String?,
    @SerializedName("book_notes") var bookNotes: String?,
    @SerializedName("number_of_pages") val numberOfPages: Int,
    @SerializedName("number_of_read_pages") var numberOfReadPages: Int,
    @SerializedName("publish_date") val publishDate: String?,
    @SerializedName("reading_status") var readingStatus: ReadingStatus?,
    @SerializedName("subjects") val subjects: List<Subject>?,
    @SerializedName("subtitle") val subtitle: String?,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String?,
    @SerializedName("wishlist") var wishlist: Boolean
) : Serializable