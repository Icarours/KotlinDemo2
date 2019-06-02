package syl.com.kotlindemo2.activity.content2

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_notify_simple.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.toast
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.activity.MainActivity
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean


/**
 * author   Bright
 * date     2019/6/2 20:38
 * desc
 * 发送简单通知
 */
class NotifySimpleActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notify_simple)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        btn_send_simple.setOnClickListener {
            val title = et_title.text.toString()
            val message = et_message.text.toString()
            sendSimpleNotify(title, message)
            toast("简单消息已推送到通知栏。点击该消息回到首页")
        }
    }

    private fun sendSimpleNotify(title: String, message: String) {
        //声明一个点击通知栏时触发的动作意图
        val clickIntent = intentFor<MainActivity>()
        val piClick = PendingIntent.getActivity(
            applicationContext,0, clickIntent, PendingIntent.FLAG_UPDATE_CURRENT
        )
        //获取系统的通知管理器
        val notifyMgr = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //开始构建简单消息的各个参数
        var builder = Notification.Builder(this)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Android 8.0开始必须给每个通知分配对应的渠道
            builder = Notification.Builder(this, getString(R.string.app_name))
        }
        val notify = builder
            .setContentIntent(piClick)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_launcher)
            //.setSubText("这里是副本")
            .setTicker("简单消息来啦")
            .setWhen(System.currentTimeMillis())
            .setLargeIcon(BitmapFactory.decodeResource(resources, R.drawable.ic_launcher))
            .setContentTitle(title)
            .setContentText(message).build()
        notifyMgr.notify(1, notify)
    }
}
