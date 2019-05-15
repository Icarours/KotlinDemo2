package syl.com.kotlindemo2.activity.content1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_department_channel.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.adapter.ChannelPagerAdapter
import syl.com.kotlindemo2.base.BaseActivity

/**
 * author   Bright
 * date     2019/5/11 15:09
 * desc
 * GridRecyclerView下拉刷新
 */
class DepartmentChannelActivity : BaseActivity() {
    private val titles = mutableListOf<String>("服装", "电器")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_department_channel)

        tl_head.setBackgroundResource(R.color.pink)
        setSupportActionBar(tl_head)
        tl_head.setNavigationOnClickListener { finish() }
        initTabLayout()
        initTabViewPager()
    }

    private fun initTabLayout() {
        tab_title.addTab(tab_title.newTab().setText(titles[0]))
        tab_title.addTab(tab_title.newTab().setText(titles[1]))
        tab_title.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(vp_content))
    }

    private fun initTabViewPager() {
        vp_content.adapter = ChannelPagerAdapter(supportFragmentManager, titles)
        vp_content.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
            override fun onPageSelected(position: Int) {
                tab_title.getTabAt(position)!!.select()
            }
        })
    }

    public override fun onStart() {
        super.onStart()
        bgChangeReceiver = BgChangeReceiver()
        val filter = IntentFilter(ChannelPagerAdapter.EVENT)
        registerReceiver(bgChangeReceiver, filter)
    }

    public override fun onStop() {
        unregisterReceiver(bgChangeReceiver)
        super.onStop()
    }

    private var bgChangeReceiver: BgChangeReceiver? = null
    private inner class BgChangeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            if (intent != null) {
                val color = intent.getIntExtra("color", Color.WHITE)
                tl_head.setBackgroundColor(color)
            }
        }
    }
}
