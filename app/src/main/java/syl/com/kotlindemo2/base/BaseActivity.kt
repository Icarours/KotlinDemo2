package syl.com.kotlindemo2.base

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import syl.com.kotlindemo2.R

/**
 * author   Bright
 * date     2019/3/19 22:16
 * desc
 * Activity的基类
 */
open class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
    }
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    open fun initToolBar(mToolbar: Toolbar) {
        mToolbar.inflateMenu(R.menu.menu1)
        mToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.item1 -> Toast.makeText(this@BaseActivity, "item1 was clicked", Toast.LENGTH_SHORT).show()
                R.id.item2 -> Toast.makeText(this@BaseActivity, "item2 was clicked", Toast.LENGTH_SHORT).show()
                R.id.item3 -> Toast.makeText(this@BaseActivity, "item3 was clicked", Toast.LENGTH_SHORT).show()
            }
            false
        }
        mToolbar.setNavigationOnClickListener { finish() }
    }
}
