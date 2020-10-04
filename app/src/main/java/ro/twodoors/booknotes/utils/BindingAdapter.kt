package ro.twodoors.booknotes.utils

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.graphics.drawable.GradientDrawable
import android.text.TextWatcher
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import androidx.core.graphics.ColorUtils
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import androidx.databinding.InverseBindingAdapter
import androidx.databinding.InverseBindingListener
import androidx.databinding.adapters.ListenerUtil
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.BlurTransformation
import ro.twodoors.booknotes.R
import ro.twodoors.booknotes.model.Author
import java.util.*

// Book Item
const val BOOK_COVER_WIDTH_140 = 140
const val BOOK_COVER_HEIGHT_200 = 200

// Work Details
const val BOOK_COVER_WIDTH_90 = 90
const val BOOK_COVER_HEIGHT_140 = 140

@BindingAdapter("app:authors")
fun TextView.setAuthors(authors : List<String>?){
    this.text = authors?.joinToString()
}

@BindingAdapter("app:bookAuthors")
fun TextView.setBookAuthors(authors : List<Author>?){
    if (authors ==  null)
        this.text = resources.getString(R.string.not_available)
    else
        this.text = authors.joinToString { it.name }
}

@BindingAdapter("app:cover")
fun ImageView.setCover(coverId: String?){
    Picasso.get().load(resources.getString(R.string.cover_url, coverId))
        .placeholder(R.drawable.ic_book_cover_unavailable)
        .resize(BOOK_COVER_WIDTH_140.toDP(), BOOK_COVER_HEIGHT_200.toDP())
        .centerInside()
        .into(this)
}

@BindingAdapter("app:bookCover")
fun ImageView.setBookCover(coverUrl: String?){
    Picasso.get().load(coverUrl)
        .placeholder(R.drawable.ic_book_cover_unavailable)
        .resize(BOOK_COVER_WIDTH_90.toDP(), BOOK_COVER_HEIGHT_140.toDP())
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
        0 -> this.text = resources.getString(R.string.not_available)
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
            _, _, _, _ ->
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

        ReadingStatus.Read ->
            this.progressTintList = ColorStateList.valueOf(resources.getColor(R.color.progress_read, null))

        else ->
            this.progressTintList = ColorStateList.valueOf(resources.getColor(R.color.progress_quit, null))

    }
}

@BindingAdapter("app:textStatusStyled")
fun TextView.setTextStatusStyled(readingStatus: ReadingStatus?){
    when(readingStatus){
        ReadingStatus.Reading ->{
            val color = resources.getColor(R.color.progress_reading, null)
            setupColors(context, this, color)
        }

        ReadingStatus.Read ->{
            val color = resources.getColor(R.color.progress_read, null)
            setupColors(context, this, color)
        }

        ReadingStatus.Unread -> {
            val color = resources.getColor(R.color.progress_unread, null)
            setupColors(context, this, color)
        }

        ReadingStatus.Quit -> {
            val color = resources.getColor(R.color.progress_quit, null)
            setupColors(context, this, color)
        }

    }
    this.text = readingStatus?.name?.toUpperCase()
}

fun setupColors(context: Context, textView: TextView, color: Int){
    textView.setTextColor(ColorStateList.valueOf(color))
    textView.setCompoundDrawablesWithIntrinsicBounds(
        setStartDrawable(context, color),
        null, null, null)
    textView.background = setBackground(color)
}

fun setBackground(color : Int) : GradientDrawable {
    return GradientDrawable().apply {
        cornerRadius = 48F
        setColor(ColorUtils.blendARGB(color, Color.WHITE, 0.8F))
    }
}

fun setStartDrawable(context: Context, color: Int) : Drawable? {
    val drawable = ContextCompat.getDrawable(context, R.drawable.ic_time)
    drawable?.colorFilter = PorterDuffColorFilter(color, PorterDuff.Mode.SRC_IN)
    return drawable
}

@BindingAdapter("app:textStatus")
fun TextView.setTextStatus(readingStatus: ReadingStatus?){
    if (this.text != readingStatus?.name)
        this.text = readingStatus?.name
}

@InverseBindingAdapter(attribute = "app:textStatus", event = "textStatusAttrChanged")
fun getTextStatus(view: TextView) : ReadingStatus? {
    if (view.text.toString().isEmpty())
        return null
    return ReadingStatus.valueOf(view.text.toString())
}

@BindingAdapter(value = ["textStatusAttrChanged"])
fun setListener(view: TextView, listener: InverseBindingListener){
    val textWatcher : TextWatcher = view.doOnTextChanged {
            _, _, _, _ ->
        listener.onChange()
    }

    val oldValue = ListenerUtil.trackListener(view, textWatcher, R.id.textWatcher)
    if (oldValue != null)
        view.removeTextChangedListener(oldValue)

    view.addTextChangedListener(textWatcher)
}

@BindingAdapter("app:bookNotes")
fun TextView.setBookNotes(bookNotes: String?){
    if (bookNotes.isNullOrEmpty())
        this.text = resources.getString(R.string.add_note)
    else
        this.text = resources.getString(R.string.view_notes)
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