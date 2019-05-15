package syl.com.kotlindemo2.activity.content1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_parcelable_second.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.bean.MessageInfo

class ParcelableSecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parcelable_second)
        //获得Parcelable格式的请求参数
        val request = intent.extras.getParcelable<MessageInfo>("message")
        tv_response.text = "收到打包好的请求消息：\n请求时间为${request.send_time}\n请求内容为${request.content}"
    }
}
