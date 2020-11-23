package ro.twodoors.booktracker.data.local.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize

data class Doc (
    @PrimaryKey
    @SerializedName("key")
    val id: String,
    @ColumnInfo(name = "author_name")
    @SerializedName("author_name")
    var authorName: List<String?>?,
    @ColumnInfo(name = "cover_edition_key")
    @SerializedName("cover_edition_key")
    var coverEditionKey: String?,
    @SerializedName("isbn")
    var isbn: List<String?>?,
    @SerializedName("publisher")
    var publisher: List<String?>?,
    @SerializedName("title")
    var title: String
) : Parcelable