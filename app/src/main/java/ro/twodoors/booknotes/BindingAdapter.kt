package ro.twodoors.booknotes

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import com.squareup.picasso.Transformation
import jp.wasabeef.picasso.transformations.BlurTransformation
import ro.twodoors.booknotes.model.Author
import ro.twodoors.booknotes.model.Doc

@BindingAdapter("app:authors")
fun TextView.setAuthors(authors : List<String>?){
    this.text = authors?.joinToString().orEmpty()
}

//@BindingAdapter("app:bookAuthors")
//fun TextView.setBookAuthors(authors : List<Author>?){
//    if (authors != null) {
//        val names : MutableList<String> = mutableListOf()
//        for(author in authors){
//            names.add(author.name)
//        }
//        this.text = names.joinToString()
//    }
//    else{
//        this.text = ""
//    }
//}

@BindingAdapter("app:bookAuthors")
fun TextView.setBookAuthors(authors : List<Author>){
    this.text = authors.joinToString { it.name }
}


@BindingAdapter("app:cover")
fun ImageView.setCover(coverId: String?){
    Picasso.get().load(getCoverUrl(coverId))
        .placeholder(R.drawable.ic_image_search)
        .error(R.drawable.ic_photo_black_24dp)
        .resize(80.toDP(), 100.toDP())
        .centerInside()
        .into(this)
}

@BindingAdapter("app:bookCover")
fun ImageView.setBookCover(coverUrl: String?){
    Picasso.get().load(coverUrl)
        .placeholder(R.drawable.ic_image_search)
        .error(R.drawable.ic_photo_black_24dp)
        .resize(80.toDP(), 100.toDP())
        .centerInside()
        .into(this)
}

@BindingAdapter("app:detailCover")
fun ImageView.setDetailCover(coverUrl: String?){
    Picasso.get().load(coverUrl)
        .placeholder(R.drawable.ic_image_search)
        .error(R.drawable.ic_photo_black_24dp)
        .resize(140.toDP(), 200.toDP())
        .centerInside()
        .into(this)
}

@BindingAdapter("app:blurCover")
fun ImageView.setBlurCover(coverUrl: String?){
    Picasso.get().load(coverUrl)
        //.placeholder(R.drawable.ic_image_search)
        //.error(R.drawable.ic_photo_black_24dp)
        .transform(BlurTransformation(context, 50, 2))
        .into(this)
}

private fun getCoverUrl(coverId: String?): String {
    return "https://covers.openlibrary.org/b/olid/" + coverId + "-L.jpg?default=false"
}

@BindingAdapter("app:editions")
fun TextView.setEditions(numberOfEditions : Int){
    this.text = resources.getQuantityString(R.plurals.editions_available, numberOfEditions, numberOfEditions)
}

@BindingAdapter("app:pages")
fun TextView.setnumberOfPages(numberOfPages : Int){
    this.text = resources.getString(R.string.number_of_pages, numberOfPages)
}

@BindingAdapter("app:published")
fun TextView.setPublishedDate(publishedDate : String){
    this.text = resources.getString(R.string.published_year, publishedDate)
}

@BindingAdapter("app:isbn")
fun TextView.setIsbn(isbn : String){
    this.text = resources.getString(R.string.isbn, isbn)
}