package ro.twodoors.booknotes.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Subject(
    @SerializedName("name") val name: String
) : Serializable
