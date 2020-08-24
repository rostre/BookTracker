package ro.twodoors.booknotes.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.book_item.view.*
import ro.twodoors.booknotes.R
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.toDP

class BookViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindBooks(book: Book) {
        itemView.title.text = book.title
        itemView.author.text = book.authorName?.joinToString().orEmpty()
        Picasso.get().load(getCoverUrl(book.coverEditionKey))
            .placeholder(R.drawable.ic_photo_black_24dp)
            .resize(120.toDP(), 180.toDP())
            .centerInside()
            .into(itemView.bookCover)
    }

    private fun getCoverUrl(coverId: String?): String {
        return "https://covers.openlibrary.org/b/olid/" + coverId + "-L.jpg?default=false"
    }

    companion object {
        fun create(parent: ViewGroup): BookViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.book_item, parent, false)
            return BookViewHolder(view)
        }
    }
}