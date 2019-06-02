package syl.com.kotlindemo2.activity.content2

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_custom_property.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import org.jetbrains.anko.toast
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.adapter.ImagePagerAdapater
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.GoodsInfo
import syl.com.kotlindemo2.bean.TitleBean

/**
 * author   Bright
 * date     2019/6/2 11:31
 * desc
 * 选项卡
 */
class CustomPropertyActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_property)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        val goodsList = GoodsInfo.defaultList
        vp_content.adapter = ImagePagerAdapater(this, goodsList)
        vp_content.currentItem = 0
        vp_content.addOnPageChangeListener(
            object : ViewPager.SimpleOnPageChangeListener() {
                override fun onPageSelected(arg0: Int) {
                    toast("您翻到的手机品牌是：${goodsList[arg0].name}")
                    Log.d(TAG, "您翻到的手机品牌是：${goodsList[arg0].name}")
                }
            })
    }

    companion object {
        private val TAG = "CustomPropertyActivity"
    }
}
