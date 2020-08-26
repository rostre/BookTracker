package ro.twodoors.booknotes.api

import com.google.gson.annotations.SerializedName
import ro.twodoors.booknotes.model.Doc

data class LibraryResponse(
    @SerializedName("docs") var docs: List<Doc> = emptyList(),
    @SerializedName("numFound") var numFound: Int?,
    @SerializedName("num_found")var num_found: Int?,
    @SerializedName("start") var start: Int?)