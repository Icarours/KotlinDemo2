package syl.com.kotlindemo2.activity.content2

import android.annotation.TargetApi
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.PRIORITY_DEFAULT
import androidx.core.app.NotificationCompat.VISIBILITY_SECRET
import kotlinx.android.synthetic.main.activity_notify_simple.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
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
    val CHANNEL_ID = "default"
    private val CHANNEL_NAME = "Default Channel"
    private val CHANNEL_DESCRIPTION = "this is default channel!"
    private var mManager: NotificationManager? = null

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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel()
            }
            sendNotification(2, title, message)
            toast("简单消息已推送到通知栏。点击该消息回到首页")
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(CHANNEL_ID, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT)
        //是否绕过请勿打扰模式
        channel.canBypassDnd()
        //闪光灯
        channel.enableLights(true)
        //锁屏显示通知
        channel.lockscreenVisibility = VISIBILITY_SECRET
        //闪关灯的灯光颜色
        channel.lightColor = Color.RED
        //桌面launcher的消息角标
        channel.canShowBadge()
        //是否允许震动
        channel.enableVibration(true)
        //获取系统通知响铃声音的配置
        channel.audioAttributes
        //获取通知取到组
        channel.group
        //设置可绕过  请勿打扰模式
        channel.setBypassDnd(true)
        //设置震动模式
        channel.vibrationPattern = longArrayOf(100, 100, 200)
        //是否会有灯光
        channel.shouldShowLights()
        getManager().createNotificationChannel(channel)
    }

    private fun getManager(): NotificationManager {
        if (mManager == null) {
            mManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        }
        return mManager as NotificationManager
    }

    /**
     * 发送通知
     */
    fun sendNotification(notifyId: Int, title: String, content: String) {
        val builder = getNotification(title, content)
        val intent = Intent(this, MainActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 100, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        builder.setContentIntent(pendingIntent)
        getManager().notify(notifyId, builder.build())
    }

    private fun getNotification(title: String, content: String): NotificationCompat.Builder {
        var builder: NotificationCompat.Builder?
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = NotificationCompat.Builder(applicationContext, CHANNEL_ID)
        } else {
            builder = NotificationCompat.Builder(applicationContext)
            builder.priority = PRIORITY_DEFAULT
        }
        //标题
        builder.setContentTitle(title)
        //文本内容
        builder.setContentText(content)
        //小图标
        builder.setSmallIcon(R.mipmap.ic_launcher)
        //设置点击信息后自动清除通知
        builder.setAutoCancel(true)
        return builder
    }
}
