package syl.com.kotlindemo2.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_act_second.*
import syl.com.kotlindemo2.R

class ActSecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_second)
        //获得请求参数的包裹
        val bundle = intent.extras
        val request_time = bundle.getString("request_time")
        val request_content = bundle.getString("request_content")
        tv_response.text = "收到请求消息：\n请求时间为${request_time}\n请求内容为${request_content}"
    }
}
