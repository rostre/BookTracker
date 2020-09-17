package ro.twodoors.booknotes.ui.booknotes

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.ui.bookinfodetailed.BookInfoDetailedFragment
import ro.twodoors.booknotes.ui.booknotesdetailed.BookNotesDetailedFragment

const val FRAGMENT_COUNT : Int = 2

@SuppressLint("WrongConstant")
class BookNotesPagerAdapter (fm: FragmentManager, private val book: Book) : FragmentStatePagerAdapter(fm, FRAGMENT_COUNT) {


    private val tabs = listOf("Notes", "Info")

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position]
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return BookNotesDetailedFragment(book)
            1 -> return BookInfoDetailedFragment(book)
            else -> return BookNotesFragment()
        }
    }

    override fun getItemPosition(`object`: Any): Int {
        return PagerAdapter.POSITION_NONE
    }

    override fun getCount(): Int = FRAGMENT_COUNT

}

