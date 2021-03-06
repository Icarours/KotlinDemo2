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
 * content1的Activity
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
            17 -> {
                transaction.replace(R.id.fl_content1, KotlinDateFragment())
                transaction.commit()
            }
            18 -> {
                transaction.replace(R.id.fl_content1, KotlinClassFragment())
                transaction.commit()
            }
            19 -> {
                transaction.replace(R.id.fl_content1, KotlinMemberFragment())
                transaction.commit()
            }
            20 -> {
                transaction.replace(R.id.fl_content1, KotlinInheritFragment())
                transaction.commit()
            }
            21 -> {
                transaction.replace(R.id.fl_content1, KotlinSpecialClassFragment())
                transaction.commit()
            }
            22 -> {
                //该条目直接跳转到一个新的Activity
            }
            23 -> {
                transaction.replace(R.id.fl_content1, KotlinCheckBoxFragment())
                transaction.commit()
            }
            24 -> {
                transaction.replace(R.id.fl_content1, KotlinRadioButtonFragment())
                transaction.commit()
            }
            25 -> {
                transaction.replace(R.id.fl_content1, KotlinLinearLayoutFragment())
                transaction.commit()
            }
            26 -> {
                transaction.replace(R.id.fl_content1, KotlinRelativeLayoutFragment())
                transaction.commit()
            }
            27 -> {
                transaction.replace(R.id.fl_content1, KotlinConstraintLayoutFragment())
                transaction.commit()
            }
            28 -> {
                transaction.replace(R.id.fl_content1, KotlinTextMarqueeFragment())
                transaction.commit()
            }
            29 -> {
                transaction.replace(R.id.fl_content1, KotlinImageScaleFragment())
                transaction.commit()
            }
            30 -> {
                transaction.replace(R.id.fl_content1, KotlinEditTextFragment())
                transaction.commit()
            }
            34 -> {
                transaction.replace(R.id.fl_content1, KotlinAlertDialogFragment())
                transaction.commit()
            }
            36 -> {
                transaction.replace(R.id.fl_content1, KotlinSpinnerDialogFragment())
                transaction.commit()
            }
            37 -> {
                transaction.replace(R.id.fl_content1, KotlinListViewFragment())
                transaction.commit()
            }
            38 -> {
                transaction.replace(R.id.fl_content1, KotlinGridViewFragment())
                transaction.commit()
            }
            39 -> {
                transaction.replace(R.id.fl_content1, KotlinRecyclerLinearFragment())
                transaction.commit()
            }
            40 -> {
                transaction.replace(R.id.fl_content1, KotlinRecyclerGridFragment())
                transaction.commit()
            }
            41 -> {
                transaction.replace(R.id.fl_content1, KotlinRecyclerStaggeredFragment())
                transaction.commit()
            }
            42 -> {
                transaction.replace(R.id.fl_content1, KotlinCoordinatorFragment())
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
