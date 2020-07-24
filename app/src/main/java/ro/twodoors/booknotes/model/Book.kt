package ro.twodoors.booknotes.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "book_table",
    foreignKeys = [ForeignKey(entity = Category::class, parentColumns = ["name"], childColumns = ["category"], onDelete = CASCADE, onUpdate = CASCADE)])
data class Book (
    @SerializedName("author_alternative_name")
    var authorAlternativeName: List<String?>?,
    @SerializedName("author_key")
    var authorKey: List<String?>?,
    @SerializedName("author_name")
    var authorName: List<String?>?,
    @SerializedName("category")
    var category: String,
    @SerializedName("contributor")
    var contributor: List<String?>?,
    @SerializedName("cover_edition_key")
    var coverEditionKey: String?,
    @SerializedName("cover_i")
    var coverI: Int?,
    @SerializedName("ebook_count_i")
    var ebookCountI: Int?,
    @SerializedName("edition_count")
    var editionCount: Int?,
    @SerializedName("edition_key")
    var editionKey: List<String?>?,
    @SerializedName("first_publish_year")
    var firstPublishYear: Int?,
    @SerializedName("first_sentence")
    var firstSentence: List<String?>?,
    @SerializedName("has_fulltext")
    var hasFulltext: Boolean?,
    @SerializedName("ia")
    var ia: List<String?>?,
    @SerializedName("ia_box_id")
    var iaBoxId: List<String?>?,
    @SerializedName("ia_collection_s")
    var iaCollectionS: String?,
    @SerializedName("ia_loaded_id")
    var iaLoadedId: List<String?>?,
    @SerializedName("isbn")
    var isbn: List<String?>?,
    @SerializedName("wishlist")
    var wishlist: Boolean?,
    @SerializedName("key")
    var key: String?,
    @SerializedName("language")
    var language: List<String?>?,
    @SerializedName("last_modified_i")
    var lastModifiedI: Int?,
    @SerializedName("lccn")
    var lccn: List<String?>?,
    @SerializedName("lending_edition_s")
    var lendingEditionS: String?,
    @SerializedName("lending_identifier_s")
    var lendingIdentifierS: String?,
    @SerializedName("oclc")
    var oclc: List<String?>?,
    @SerializedName("person")
    var person: List<String?>?,
    @SerializedName("place")
    var place: List<String?>?,
    @SerializedName("publish_date")
    var publishDate: List<String?>?,
    @SerializedName("publisher")
    var publisher: List<String?>?,
    @SerializedName("seed")
    var seed: List<String?>?,
    @SerializedName("subject")
    var subject: List<String?>?,
    @SerializedName("text")
    var text: List<String?>?,
    @SerializedName("time")
    var time: List<String?>?,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("title")
    var title: String,
    @SerializedName("title_suggest")
    var titleSuggest: String?,
    @SerializedName("type")
    var type: String?
)