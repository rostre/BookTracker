package ro.twodoors.booknotes.ui.reading.status

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.PagerAdapter
import ro.twodoors.booknotes.ui.reading.quitted.QuittedFragment
import ro.twodoors.booknotes.ui.reading.read.ReadFragment
import ro.twodoors.booknotes.ui.reading.reading.ReadingFragment
import ro.twodoors.booknotes.ui.reading.unread.UnreadFragment

class ReadingPagerAdapter(fm: FragmentManager, val fragmentCount : Int) : FragmentStatePagerAdapter(fm, fragmentCount) {

    override fun getItem(position: Int): Fragment {

        when(position){
            0-> return ReadingFragment()
            1-> return ReadFragment()
            2-> return QuittedFragment()
            3-> return UnreadFragment()
            else -> return ReadingFragment()
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return ReadingStatus.values()[position].name
    }

    override fun getCount(): Int = fragmentCount

}

enum class ReadingStatus {
    Reading,
    Read,
    Quitted,
    Unread

}