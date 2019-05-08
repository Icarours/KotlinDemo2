package syl.com.kotlindemo2.activity

import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_tab_layout.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.adapter.GoodsPagerAdapter
import syl.com.kotlindemo2.base.BaseActivity

/**
 * author   Bright
 * date     2019/5/8 23:18
 * desc
 * TabLayout+ToolBar+ViewPager
 */
class TabLayoutActivity : BaseActivity(), TabLayout.OnTabSelectedListener {
    override fun onTabReselected(tab: TabLayout.Tab?) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        //To change body of created functions use File | Settings | File Templates.
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        //To change body of created functions use File | Settings | File Templates.
        vp_content.currentItem = tab!!.position
    }


    private val titles = mutableListOf<String>("商品", "详情")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tab_layout)

        //使用自定义的工具栏替换系统默认的导航栏
        setSupportActionBar(tl_head)
        tl_head.setNavigationOnClickListener { finish() }
        initTabLayout()
        initTabViewPager()
    }

    //初始化头部的文本标签
    private fun initTabLayout() {
        tab_title.addTab(tab_title.newTab().setText(titles[0]))
        tab_title.addTab(tab_title.newTab().setText(titles[1]))
        tab_title.addOnTabSelectedListener(this)
    }

    //初始化页面主体的翻页视图
    private fun initTabViewPager() {
        vp_content.adapter = GoodsPagerAdapter(supportFragmentManager, titles)
        //利用object关键字表示声明一个匿名实例
        vp_content.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                //翻页操作停止后，同步切换到对应的文本标签
                tab_title.getTabAt(position)!!.select()
            }
        })
    }
}
