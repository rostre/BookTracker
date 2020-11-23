package ro.twodoors.booktracker.data.local.model

import com.google.gson.annotations.SerializedName
import ro.twodoors.booktracker.data.local.model.Doc

data class LibraryResponse(
    @SerializedName("docs") var docs: List<Doc> = emptyList(),
    @SerializedName("numFound") var numFound: Int?,
    @SerializedName("num_found")var num_found: Int?,
    @SerializedName("start") var start: Int?)