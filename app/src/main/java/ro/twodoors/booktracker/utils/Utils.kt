package ro.twodoors.booktracker.utils

import androidx.recyclerview.widget.DiffUtil
import ro.twodoors.booktracker.data.local.model.Book

class Utils{

    companion object {
        val BOOK_COMPARATOR = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean =
                oldItem == newItem
        }
    }

}

enum class ReadingStatus {
    Reading,
    Read,
    Quit,
    Unread
}

enum class ItemType {
    LIST,
    GRID
}

