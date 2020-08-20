package ro.twodoors.booknotes.api

import com.google.gson.annotations.SerializedName
import ro.twodoors.booknotes.model.Book

data class LibraryResponse(
    @SerializedName("docs") var docs: List<Book> = emptyList(),
    @SerializedName("numFound") var numFound: Int?,
    @SerializedName("num_found")var num_found: Int?,
    @SerializedName("start") var start: Int?)