package ro.twodoors.booknotes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.book_item.view.*
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.model.OpenLibraryResponse

class BookAdapter (private val bookList: OpenLibraryResponse) : RecyclerView.Adapter<BookAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.book_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        bookList.docs[position]?.let { holder.bindBooks(it) }
    }

    override fun getItemCount(): Int = bookList.docs.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindBooks(book: Book) {
            itemView.title.text = book.title
            itemView.author.text = book.authorName?.get(0).orEmpty()
            Picasso.get().load(getCoverUrl(book.coverEditionKey))
                .placeholder(R.drawable.ic_photo_black_24dp)
                .resize(120.toDP(), 180.toDP())
                .centerInside()
                .into(itemView.bookCover)
        }

        private fun getCoverUrl(coverId: String?): String {
            return "https://covers.openlibrary.org/b/olid/" + coverId + "-L.jpg?default=false"
        }
    }
}

