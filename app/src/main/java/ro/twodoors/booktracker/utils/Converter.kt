package ro.twodoors.booktracker.utils

import androidx.room.TypeConverter
import ro.twodoors.booktracker.data.local.model.Author
import ro.twodoors.booktracker.data.local.model.Cover
import ro.twodoors.booktracker.data.local.model.Subject

class Converter {

    @TypeConverter
    fun fromList(authors: List<Author>?): String? {
        return authors?.joinToString { it.name }
    }

    @TypeConverter
    fun fromString(value: String?): List<Author>? {
        val authors = value?.split(",")
        val list = mutableListOf<Author>()
        authors?.forEach { author ->
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
            with(list) { add(
                Subject(
                    name = subject
                )
            ) }
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

    //    @TypeConverter
//    fun fromNotesToString(list: List<Note>?): String? {
//        val value: StringBuilder = StringBuilder()
//        if (list != null) {
//            list.forEach { note ->
//                value.append("startNote:").append(note.value).append(":endNote")
//            }
//            return value.toString()
//        }
//        return null
//    }
//
//    @TypeConverter
//    fun fromStringToNotes(value: String?): List<Note>? {
//        val notes = value?.split("startNote:", ":endNote")
//        val list = mutableListOf<Note>()
//        notes?.forEach { note ->
//            with(list) { add(Note(note)) }
//        }
//        return list
//    }

}