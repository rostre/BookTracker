package ro.twodoors.booknotes

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import ro.twodoors.booknotes.model.Doc

@BindingAdapter("app:authors")
fun TextView.setAuthors(doc: Doc){
    this.text = doc.authorName?.joinToString().orEmpty()
}

@BindingAdapter("app:cover")
fun ImageView.setCover(coverId: String?){
Picasso.get().load(getCoverUrl(coverId))
    .placeholder(R.drawable.ic_image_search)
    .error(R.drawable.ic_photo_black_24dp)
    .resize(120.toDP(), 180.toDP())
    .centerInside()
    .into(this)
}

private fun getCoverUrl(coverId: String?): String {
    return "https://covers.openlibrary.org/b/olid/" + coverId + "-L.jpg?default=false"
}
