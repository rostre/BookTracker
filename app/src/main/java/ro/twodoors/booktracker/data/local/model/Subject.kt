package ro.twodoors.booktracker.data.local.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Subject(
    @SerializedName("name") val name: String
) : Serializable
