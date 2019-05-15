package syl.com.kotlindemo2.activity.content1

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_broad_system.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.util.DateUtil

/**
 * author   Bright
 * date     2019/5/11 14:18
 * desc
 * 系统广播
 */
class BroadSystemActivity : BaseActivity() {
    var desc = "开始侦听分钟广播，请稍等。注意要保持屏幕亮着，才能正常收到广播"
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_broad_system)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        tv_system.text = desc
        tv_system.movementMethod = ScrollingMovementMethod.getInstance()
    }

    override fun onStart() {
        super.onStart()
        timeReceiver = TimeReceiver()
        //声明一个过滤器，只接收名称为Intent.ACTION_TIME_TICK的分钟广播
        val filter = IntentFilter(Intent.ACTION_TIME_TICK)
        //在活动启动时注册广播接收器
        registerReceiver(timeReceiver, filter)
    }

    override fun onStop() {
        super.onStop()
        //在活动停止时注销广播接收器
        unregisterReceiver(timeReceiver)
    }

    private var timeReceiver: TimeReceiver? = null

    //定义一个时间广播的接收器
    inner class TimeReceiver : BroadcastReceiver() {
        override fun onReceive(context: Context, intent: Intent?) {
            if (intent != null) {
                desc = "$desc\n${DateUtil.nowDateTime} 收到一个${intent.action}广播"
                tv_system.text = desc
            }
        }
    }

}
