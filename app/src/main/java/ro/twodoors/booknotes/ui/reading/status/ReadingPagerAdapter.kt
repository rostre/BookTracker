package ro.twodoors.booknotes.ui.reading.status

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import ro.twodoors.booknotes.ui.reading.quitted.QuittedFragment
import ro.twodoors.booknotes.ui.reading.read.ReadFragment
import ro.twodoors.booknotes.ui.reading.reading.ReadingFragment
import ro.twodoors.booknotes.ui.reading.unread.UnreadFragment
import ro.twodoors.booknotes.utils.ReadingStatus

private const val POSITION_READING = 0
private const val POSITION_READ = 1
private const val POSITION_QUIT = 2
private const val POSITION_UNREAD = 3

class ReadingPagerAdapter(fm: FragmentManager, val fragmentCount : Int) : FragmentStatePagerAdapter(fm, fragmentCount) {

    override fun getItem(position: Int): Fragment {

        return when(position){
            POSITION_READING -> ReadingFragment()
            POSITION_READ -> ReadFragment()
            POSITION_QUIT -> QuittedFragment()
            POSITION_UNREAD -> UnreadFragment()
            else -> ReadingFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return ReadingStatus.values()[position].name
    }

    override fun getCount(): Int = fragmentCount

}