package task.interview.com.dynamicviewpager

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class DynamicViewPagerAdapter(val fm: FragmentManager, val count_fragments: Int) : FragmentStatePagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        when (position) {
            0 -> {
                return Fragment_One()
            }
            1 -> {
                return Fragment_Two()
            }
            2 -> {
                return Fragment_Three()
            }
            3 -> {
                return Fragment_Four()
            }
            else -> {
                return Fragment_One()
            }

        }
    }

    override fun getCount(): Int {
        return count_fragments
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> {
                return "Fragment_One"
            }
            1 -> {
                return "Fragment_Two"
            }
            2 -> {
                return "Fragment_Three"
            }
            3 -> {
                return "Fragment_Four"
            }
            else -> {
                return "Fragment_One"
            }
        }
    }

}