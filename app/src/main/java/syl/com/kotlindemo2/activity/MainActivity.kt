package syl.com.kotlindemo2.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.fragment.ContentFragment1
import syl.com.kotlindemo2.fragment.ContentFragment2
import syl.com.kotlindemo2.fragment.ContentFragment3

class MainActivity : AppCompatActivity() {
    var fragments: MutableList<Fragment> = ArrayList()
    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        when (item.itemId) {
            R.id.navigation_home -> {
                transaction.replace(R.id.fl, ContentFragment1())
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                transaction.replace(R.id.fl, ContentFragment2())
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                transaction.replace(R.id.fl, ContentFragment3())
                transaction.commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.fl, ContentFragment1())
        transaction.commit()
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
    }

    internal inner class MyAdapter : FragmentPagerAdapter {
        constructor(fm: FragmentManager) : super(fm) {
            fragments.add(ContentFragment1())
            fragments.add(ContentFragment2())
            fragments.add(ContentFragment3())
        }

        override fun getItem(position: Int): Fragment {
            return fragments[position]
        }

        override fun getCount(): Int = fragments.size
    }
}
