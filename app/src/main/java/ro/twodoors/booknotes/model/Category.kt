package ro.twodoors.booknotes.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "category_table")
data class Category(@PrimaryKey var name: String)