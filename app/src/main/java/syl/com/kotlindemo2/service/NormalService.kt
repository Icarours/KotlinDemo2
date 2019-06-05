package syl.com.kotlindemo2.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
import syl.com.kotlindemo2.activity.content2.ServiceNormalActivity

/**
 * Created by Bright on 2019/6/5.
 * @Describe
 * @Called
 */
class NormalService : Service() {

    override fun onCreate() {
        ServiceNormalActivity.showText("创建服务")
        super.onCreate()
    }

    override fun onStartCommand(intent: Intent, flags: Int, startid: Int): Int {
        val bundle = intent.extras
        val request_content = bundle.getString("request_content")
        ServiceNormalActivity.showText("启动服务，收到请求内容：${request_content}")
        return START_STICKY
    }

    override fun onDestroy() {
        ServiceNormalActivity.showText("停止服务")
        super.onDestroy()
    }

    override fun onBind(intent: Intent): IBinder? = null

}