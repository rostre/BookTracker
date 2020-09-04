package ro.twodoors.booknotes.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ro.twodoors.booknotes.setBookAuthors

class Converter {

//    @TypeConverter
//    fun fromList(list: List<String?>?): String? {
//        val gson = Gson()
//        return gson.toJson(list)
//    }
//
//    @TypeConverter
//    fun fromString(value: String?): List<String>? {
//        val listType = object : TypeToken<List<String>>() {}.type
//        return Gson().fromJson(value, listType)
//    }

    @TypeConverter
    fun fromList(authors: List<Author>): String {
        return authors.joinToString { it.name }
    }


    @TypeConverter
    fun fromString(value: String): List<Author> {
        val authors = value.split(",")
        val list = mutableListOf<Author>()
        authors.forEach { author ->
            with(list) { add(Author(name = author)) }
        }
        return list
    }

    @TypeConverter
    fun fromCover(cover: Cover?): String? {
        return cover?.medium
    }

    @TypeConverter
    fun toCover(value: String?): Cover? {
        return value?.let { Cover(medium = it) }
    }

    @TypeConverter
    fun fromSubjects(subjects: List<Subject>?): String? {
        return subjects?.joinToString { it.name }
    }


    @TypeConverter
    fun toSubjects(value: String?): List<Subject>? {
        val subjects = value?.split(",")
        val list = mutableListOf<Subject>()
        subjects?.forEach { subject ->
            with(list) { add(Subject(name = subject)) }
        }
        return list
    }

}