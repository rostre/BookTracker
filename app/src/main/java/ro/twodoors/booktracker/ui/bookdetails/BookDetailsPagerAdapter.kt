package ro.twodoors.booktracker.ui.bookdetails

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ro.twodoors.booktracker.ui.bookinfo.BookInfoFragment
import ro.twodoors.booktracker.ui.booknotes.BookNotesFragment

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
            BOOK_NOTES_DETAILED -> BookNotesFragment(bookId)
            BOOK_INFO_DETAILED -> BookInfoFragment(bookId)
            else -> BookDetailsFragment()
        }
    }

    override fun getCount(): Int = FRAGMENT_COUNT

}

