package syl.com.kotlindemo2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import syl.com.kotlindemo2.bean.GoodsInfo
import syl.com.kotlindemo2.fragment.content1.BroadcastFragment

class BroadcastPagerAdapter(fm: FragmentManager, private val goodsList: MutableList<GoodsInfo>) : FragmentStatePagerAdapter(fm) {

    override fun getCount(): Int = goodsList.size

    override fun getItem(position: Int): Fragment {
        return BroadcastFragment.newInstance(position,
                goodsList[position].pic, goodsList[position].desc)
    }

    override fun getPageTitle(position: Int): CharSequence = goodsList[position].name

}

