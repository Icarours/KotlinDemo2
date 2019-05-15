package syl.com.kotlindemo2.activity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.fragment.content1.Demo1Fragment
import syl.com.kotlindemo2.fragment.content2.MessageFragment
import syl.com.kotlindemo2.fragment.content2.ProgressDialogFragment

/**
 * author   Bright
 * date     2019/5/11 18:59
 * desc
 * content2的Activity
 */
class Content2Activity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_content2)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        val mToolbar = findViewById<Toolbar>(R.id.toolbar)
        mToolbar.title = titleBean.title
        mToolbar.title = titleBean.title
        mToolbar.subtitle = titleBean.description
        initToolBar(mToolbar)

        initFragment(titleBean)
    }

    private fun initFragment(titleBean: TitleBean) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        when (titleBean.id) {
            0 -> {
                transaction.replace(R.id.fl_content2, MessageFragment())
                transaction.commit()
            }
            1 -> {
                transaction.replace(R.id.fl_content2, ProgressDialogFragment())
                transaction.commit()
            }
            else -> {
                val fragment = Demo1Fragment()
                transaction.replace(R.id.fl_content2, fragment)
                val args = Bundle()
                args.putParcelable("title", titleBean)
                fragment.arguments = args
                transaction.commit()
            }
        }
    }
}
