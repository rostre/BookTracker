package ro.twodoors.booknotes.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "book_table",
    foreignKeys = [ForeignKey(entity = Category::class, parentColumns = ["name"], childColumns = ["category"], onDelete = ForeignKey.CASCADE, onUpdate = ForeignKey.CASCADE)])
data class Book(
    @PrimaryKey
    @SerializedName("key") val id: String,
    @SerializedName("authors") val authors: List<Author>,
    @SerializedName("category") var category: String?,
    @SerializedName("cover") val cover: Cover?,
    @SerializedName("description") val description: String?,
    @SerializedName("number_of_pages") val numberOfPages: Int,
    @SerializedName("publish_date") val publishDate: String?,
    @SerializedName("subjects") val subjects: List<Subject>?,
    @SerializedName("subtitle") val subtitle: String?,
    @SerializedName("title") val title: String,
    @SerializedName("url") val url: String?,
    @SerializedName("wishlist") var wishlist: Boolean
) : Serializable