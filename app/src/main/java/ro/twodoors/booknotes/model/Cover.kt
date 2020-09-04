package ro.twodoors.booknotes.model

import com.google.gson.annotations.SerializedName

data class Cover(
    //@SerializedName("small") val small: String,
    @SerializedName("medium") val medium: String
    //@SerializedName("large") val large: String
)