package ro.twodoors.booknotes.utils

import android.content.res.ColorStateList
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.databinding.adapters.ListenerUtil
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import ro.twodoors.booknotes.R
import ro.twodoors.booknotes.model.Author
import ro.twodoors.booknotes.ui.reading.status.ReadingStatus
import java.util.*

@BindingAdapter("app:authors")
fun TextView.setAuthors(authors : List<String>?){
    this.text = authors?.joinToString().orEmpty()
}

@BindingAdapter("app:bookAuthors")
fun TextView.setBookAuthors(authors : List<Author>?){
    this.text = authors?.joinToString { it.name }
}


@BindingAdapter("app:cover")
fun ImageView.setCover(coverId: String?){
    Picasso.get().load(resources.getString(R.string.cover_url, coverId))
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
        .transform(BlurTransformation(context, 50, 2))
        .into(this)
}


@BindingAdapter("app:editions")
fun TextView.setEditions(numberOfEditions : Int){
    this.text = resources.getQuantityString(R.plurals.editions_available, numberOfEditions, numberOfEditions)
}

@BindingAdapter("app:pages")
fun TextView.setnumberOfPages(numberOfPages : Int){
    when(numberOfPages){
        0 -> this.text = "N/A"
        else -> this.text = resources.getString(R.string.number_of_pages, numberOfPages)
    }
}

@BindingAdapter("app:countPages")
fun TextView.setNumberOfPages(numberOfPages : Int){
    if (this.text != numberOfPages.toString())
        this.text = numberOfPages.toString()
}

@InverseBindingAdapter(attribute = "app:countPages", event = "countPagesAttrChanged")
fun getNumberOfPages(view: TextView) : Int {
    return view.text.toString().toInt()
}

@BindingAdapter(value = ["countPagesAttrChanged"])
fun setCountPagesAttrChangedListener(view: TextView, listener: InverseBindingListener){
    val textWatcher : TextWatcher = view.doOnTextChanged {
            text, start, before, count ->
        listener.onChange()
    }

    val oldValue = ListenerUtil.trackListener(view, textWatcher, R.id.textWatcher)
    if (oldValue != null)
        view.removeTextChangedListener(oldValue)

    view.addTextChangedListener(textWatcher)
}

@BindingAdapter("app:labelVisibility")
fun TextView.setLabelVisibility(readingStatus: ReadingStatus){
    when(readingStatus){
        ReadingStatus.Unread -> this.visibility = View.INVISIBLE
        else -> this.visibility = View.VISIBLE
    }
}

@BindingAdapter("app:progressVisibility")
fun ProgressBar.setProgressVisibility(readingStatus: ReadingStatus){
    when(readingStatus){
        ReadingStatus.Unread -> this.visibility = View.INVISIBLE
        else -> this.visibility = View.VISIBLE
    }
}

@BindingAdapter(value = arrayOf("app:readPages", "app:totalPages"), requireAll = true)
fun ProgressBar.setReadingProgress(numberOfReadPages : Int, totalNumberOfPages : Int){
    when(totalNumberOfPages){
        0 -> this.progress = 0
        else -> this.progress = numberOfReadPages
    }
}

@BindingAdapter(value = arrayOf("app:readPages", "app:totalPages"), requireAll = true)
fun TextView.setTextProgress(numberOfReadPages : Int, totalNumberOfPages: Int){
    when(totalNumberOfPages){
        0 -> this.text = "0 %"
        else -> {
            val progressPercentage = ( numberOfReadPages * 100 / totalNumberOfPages)
            this.text = resources.getString(R.string.progress_percentage, progressPercentage)
        }
    }
}

@BindingAdapter("app:progressTint")
fun ProgressBar.setProgressTint(readingStatus: ReadingStatus){
    when(readingStatus){
        ReadingStatus.Reading ->
            this.progressTintList = ColorStateList.valueOf(resources.getColor(R.color.progress_reading, null))
            //this.progressBackgroundTintList

        ReadingStatus.Read ->
            this.progressTintList = ColorStateList.valueOf(resources.getColor(R.color.progress_read, null))
            //this.progressBackgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.progress_read, null))
        else ->

            this.progressTintList = ColorStateList.valueOf(resources.getColor(R.color.progress_quit, null))
//            this.progressBackgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.progress_quit, null))
    }
}

@BindingAdapter("app:textStatusStyled")
fun TextView.setTextStatusStyled(readingStatus: ReadingStatus?){
    when(readingStatus){
        ReadingStatus.Reading ->
            this.setTextColor(ColorStateList.valueOf(resources.getColor(R.color.progress_reading, null)))

        ReadingStatus.Read ->
            this.setTextColor(ColorStateList.valueOf(resources.getColor(R.color.progress_read, null)))

        ReadingStatus.Unread ->
            this.setTextColor(ColorStateList.valueOf(resources.getColor(R.color.progress_unread, null)))

        ReadingStatus.Quitted ->
            this.setTextColor(ColorStateList.valueOf(resources.getColor(R.color.progress_quit, null)))
    }
    this.text = readingStatus?.name
}

@BindingAdapter("app:textStatus")
fun TextView.setTextStatus(readingStatus: ReadingStatus){
    if (this.text != readingStatus.name)
        this.text = readingStatus.name
}

@InverseBindingAdapter(attribute = "app:textStatus", event = "textStatusAttrChanged")
fun getTextStatus(view: TextView) : ReadingStatus {
    return ReadingStatus.valueOf(view.text.toString())
}

@BindingAdapter(value = ["textStatusAttrChanged"])
fun setListener(view: TextView, listener: InverseBindingListener){
    val textWatcher : TextWatcher = view.doOnTextChanged {
            text, start, before, count ->
        listener.onChange()
    }

    val oldValue = ListenerUtil.trackListener(view, textWatcher, R.id.textWatcher)
    if (oldValue != null)
        view.removeTextChangedListener(oldValue)

    view.addTextChangedListener(textWatcher)
}

@BindingAdapter("app:published")
fun TextView.setPublishedDate(publishedDate : String?){
    publishedDate?.let {
        this.text = resources.getString(R.string.published_year, publishedDate)
    }

}

@BindingAdapter("app:isbn")
fun TextView.setIsbn(isbn : String?){
    isbn?.let {
        this.text = resources.getString(R.string.isbn, isbn)
    }
}

@BindingAdapter("app:group_visibility")
fun Group.setVisibility(value : String?) {
    if (value.isNullOrEmpty())
        this.visibility = View.GONE
    else
        this.visibility = View.VISIBLE
}

fun getCurrentDateTime(): Long {
    return Calendar.getInstance().timeInMillis
}