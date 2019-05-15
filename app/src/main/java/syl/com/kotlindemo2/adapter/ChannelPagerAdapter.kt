package syl.com.kotlindemo2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import syl.com.kotlindemo2.fragment.content1.AppliancesFragment
import syl.com.kotlindemo2.fragment.content1.ClothesFragment


class ChannelPagerAdapter(fm: FragmentManager, private val titles: MutableList<String>) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = when (position) {
        0 -> ClothesFragment()
        1 -> AppliancesFragment()
        else -> ClothesFragment()
    }

    override fun getCount(): Int = titles.size

    override fun getPageTitle(position: Int): CharSequence = titles[position]

    companion object {
        const val EVENT = "com.example.complex.adapter.ChannelPagerAdapter"
    }
}
