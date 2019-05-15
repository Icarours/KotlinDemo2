package syl.com.kotlindemo2.activity.content1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_broad_temp.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.adapter.BroadcastPagerAdapter
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.GoodsInfo
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.fragment.content1.BroadcastFragment

/**
 * author   Bright
 * date     2019/5/11 13:38
 * desc
 * 广播
 */
class BroadTempActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broad_temp)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        pts_tab.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f)
        vp_content.adapter = BroadcastPagerAdapter(supportFragmentManager, GoodsInfo.defaultList)
        vp_content.currentItem = 0
    }

    public override fun onStart() {
        super.onStart()
        bgChangeReceiver = BgChangeReceiver()
        //声明一个过滤器，明确只接收名称为BroadcastFragment.EVENT的广播
        val filter = IntentFilter(BroadcastFragment.EVENT)
        //在活动启动时注册广播接收器
        registerReceiver(bgChangeReceiver, filter)
    }

    public override fun onStop() {
        //在活动停止时注销广播接收器
        unregisterReceiver(bgChangeReceiver)
        super.onStop()
    }

    private var bgChangeReceiver: BgChangeReceiver? = null
    //定义一个背景色变更的广播接收器
    private inner class BgChangeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            if (intent != null) {
                //从广播消息中获取新颜色，并将页面背景色修改成新颜色
                val color = intent.getIntExtra("color", Color.WHITE)
                ll_brd_temp.setBackgroundColor(color)
            }
        }
    }
}
