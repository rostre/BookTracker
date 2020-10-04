package ro.twodoors.booknotes.ui.booknotes

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import ro.twodoors.booknotes.model.Book
import ro.twodoors.booknotes.ui.bookinfodetailed.BookInfoDetailedFragment
import ro.twodoors.booknotes.ui.booknotesdetailed.BookNotesDetailedFragment

const val BOOK_NOTES_DETAILED = 0
const val BOOK_INFO_DETAILED = 1
const val FRAGMENT_COUNT : Int = 2

@SuppressLint("WrongConstant")
class BookNotesPagerAdapter (fm: FragmentManager, private val bookId: String) : FragmentStatePagerAdapter(fm, FRAGMENT_COUNT) {

    private val tabs = listOf("Notes", "Info")

    override fun getPageTitle(position: Int): CharSequence? {
        return tabs[position]
    }

    override fun getItem(position: Int): Fragment {
        return when(position){
            BOOK_NOTES_DETAILED -> BookNotesDetailedFragment(bookId)
            BOOK_INFO_DETAILED -> BookInfoDetailedFragment(bookId)
            else -> BookNotesFragment()
        }
    }

    override fun getCount(): Int = FRAGMENT_COUNT

}

