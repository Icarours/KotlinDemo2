package syl.com.kotlindemo2.activity.content1

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import androidx.annotation.RequiresApi
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_view_pager.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import org.jetbrains.anko.toast
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.adapter.ImagePagerAdapater
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.GoodsInfo
import syl.com.kotlindemo2.bean.TitleBean

/**
 * author   Bright
 * date     2019/5/8 22:24
 * desc
 * ViewPager
 */
class ViewPagerActivity : BaseActivity(), ViewPager.OnPageChangeListener {
    private var goodsList = GoodsInfo.defaultList
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_pager)

        val intent = intent
        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean!!.title
        toolbar.title = titleBean!!.title
        toolbar.subtitle = titleBean!!.description
        initToolBar(toolbar)

        //注意PagerTabStrip不存在textSize属性，只能调用setTextSize方法设置文字大小
        pts_tab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        pts_tab.setTextColor(Color.GREEN)
        //取消Tab 下面的长横线
        pts_tab.drawFullUnderline=false
        vp_content.adapter = ImagePagerAdapater(this, goodsList)
        vp_content.currentItem = 0
        vp_content.addOnPageChangeListener(this)
    }

    override fun onPageScrollStateChanged(state: Int) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onPageSelected(position: Int) {
        //To change body of created functions use File | Settings | File Templates.
        toast("您翻到的手机品牌是：${goodsList[position].name}")
    }
}
