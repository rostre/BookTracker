package ro.twodoors.booknotes.utils

import androidx.room.TypeConverter
import ro.twodoors.booknotes.model.Author
import ro.twodoors.booknotes.model.Cover
import ro.twodoors.booknotes.model.Subject
import ro.twodoors.booknotes.ui.reading.status.ReadingStatus

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

    @TypeConverter
    fun stringToReadingStatus(status: String?): ReadingStatus? {
        return status?.let { ReadingStatus.valueOf(it) }
    }

    @TypeConverter
    fun readingStatusToString(readingStatus: ReadingStatus?): String? {
        return readingStatus?.name
    }

}