package ro.twodoors.booknotes.model

import com.google.gson.annotations.SerializedName

data class OpenLibraryResponse(@SerializedName("docs")
                               var docs: List<Book?>,
                               @SerializedName("numFound")
                               var numFound: Int?,
                               var num_found: Int?,
                               @SerializedName("start")
                               var start: Int?)