package ro.twodoors.booknotes.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "book_table",
    foreignKeys = [ForeignKey(entity = Category::class, parentColumns = ["name"], childColumns = ["category"], onDelete = CASCADE, onUpdate = CASCADE)])
data class Doc (
    @PrimaryKey
    @SerializedName("key")
    val id: String,
    @SerializedName("author_name")
    var authorName: List<String?>?,
    @SerializedName("category")
    var category: String?,
    @SerializedName("cover_edition_key")
    var coverEditionKey: String?,
    @SerializedName("cover_i")
    var coverI: Int?,
    @SerializedName("isbn")
    var isbn: List<String?>?,
    @SerializedName("wishlist")
    var wishlist: Boolean,
    @SerializedName("publisher")
    var publisher: List<String?>?,
    @SerializedName("title")
    var title: String,
    @SerializedName("type")
    var type: String?
)