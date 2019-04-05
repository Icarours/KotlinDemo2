package syl.com.kotlindemo2.activity

import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.Window
import androidx.appcompat.widget.Toolbar
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.fragment.content1.*
/**
 * author   Bright
 * date     2019/4/5 15:34
 * desc
 * Activity的基类
 */
class Content1Activity : BaseActivity() {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_content1)
        val intent = intent
        val titleBean = intent.getParcelableExtra("title") as TitleBean
        val mToolbar = findViewById<Toolbar>(R.id.toolbar)
        mToolbar.title = titleBean!!.title
        mToolbar.title = titleBean!!.title
        mToolbar.subtitle = titleBean!!.description
        initToolBar(mToolbar)
        initFragment(titleBean)
    }

    private fun initFragment(titleBean: TitleBean) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        when (titleBean.id) {
            0 -> {
                transaction.replace(R.id.fl_content1, BtnFragment())
                transaction.commit()
            }
            1 -> {
                val valueFragment = PassValueFragment()
                transaction.replace(R.id.fl_content1, valueFragment)
                val argsv = Bundle()
                argsv.putParcelable("title", titleBean)
                argsv.putString("key1", "value1")
                argsv.putString("key2", "value2")
                valueFragment.arguments = argsv
                transaction.commit()
            }
            2 -> {
                val valueFragment = PassValueFragment()
                transaction.replace(R.id.fl_content1, valueFragment)
                val args = Bundle()
                args.putParcelable("title", titleBean)
                valueFragment.arguments = args
                transaction.commit()
            }
            3 -> {
                transaction.replace(R.id.fl_content1, AnkoFragment())
                transaction.commit()
            }
            4 -> {
                transaction.replace(R.id.fl_content1, KotlinStart3Fragment())
                transaction.commit()
            }
            5 -> {
                transaction.replace(R.id.fl_content1, KotlinArrayFragment())
                transaction.commit()
            }
            6 -> {
                transaction.replace(R.id.fl_content1, KotlinStringFragment())
                transaction.commit()
            }
            7 -> {
                transaction.replace(R.id.fl_content1, KotlinSetFragment())
                transaction.commit()
            }
            8 -> {
                transaction.replace(R.id.fl_content1, KotlinListFragment())
                transaction.commit()
            }
            9 -> {
                transaction.replace(R.id.fl_content1, KotlinMapFragment())
                transaction.commit()
            }
            10 -> {
                transaction.replace(R.id.fl_content1, KotlinConditionFragment())
                transaction.commit()
            }
            11 -> {
                transaction.replace(R.id.fl_content1, KotlinRepeatFragment())
                transaction.commit()
            }
            12 -> {
                transaction.replace(R.id.fl_content1, KotlinNullFragment())
                transaction.commit()
            }
            13 -> {
                transaction.replace(R.id.fl_content1, KotlinEqualFragment())
                transaction.commit()
            }
            14 -> {
                transaction.replace(R.id.fl_content1, KotlinFunctionFragment())
                transaction.commit()
            }
            15 -> {
                transaction.replace(R.id.fl_content1, KotlinParamFragment())
                transaction.commit()
            }
            16 -> {
                transaction.replace(R.id.fl_content1, KotlinSpecialFunFragment())
                transaction.commit()
            }
            else -> {
                val fragment = Demo1Fragment()
                transaction.replace(R.id.fl_content1, fragment)
                val args = Bundle()
                args.putParcelable("title", titleBean)
                fragment.arguments = args
                transaction.commit()
            }
        }
    }
}
