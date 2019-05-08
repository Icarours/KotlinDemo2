package syl.com.kotlindemo2.activity

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_fragment_dynamic.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.adapter.MobilePagerAdapter
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.GoodsInfo
import syl.com.kotlindemo2.bean.TitleBean

/**
 * author   Bright
 * date     2019/5/8 23:03
 * desc
 * Fragment 碎片化
 */
class FragmentDynamicActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_dynamic)

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
//        pts_tab.drawFullUnderline = false
        //碎片适配器需要传入碎片管理器对象supportFragmentManager
        vp_content.adapter = MobilePagerAdapter(supportFragmentManager, GoodsInfo.defaultList)
        vp_content.currentItem = 0
    }
}
