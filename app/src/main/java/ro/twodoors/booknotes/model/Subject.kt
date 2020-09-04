package ro.twodoors.booknotes.model

import com.google.gson.annotations.SerializedName

data class Subject(
    @SerializedName("name") val name: String
)
