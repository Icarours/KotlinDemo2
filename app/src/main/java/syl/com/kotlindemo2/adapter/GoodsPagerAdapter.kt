package syl.com.kotlindemo2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import syl.com.kotlindemo2.fragment.BookCoverFragment
import syl.com.kotlindemo2.fragment.BookDetailFragment


class GoodsPagerAdapter(fm: FragmentManager, private val titles: MutableList<String>) : FragmentPagerAdapter(fm) {

    //根据位置序号分别指定不同的Fragment碎片类
    override fun getItem(position: Int): Fragment = when (position) {
        0 -> BookCoverFragment()
        1 -> BookDetailFragment()
        else -> BookCoverFragment()
    }

    override fun getCount(): Int = titles.size

    override fun getPageTitle(position: Int): CharSequence = titles[position]
}
