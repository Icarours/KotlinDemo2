package syl.com.kotlindemo2.activity

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_act_response.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.bean.MessageInfo
import syl.com.kotlindemo2.util.DateUtil

class ActResponseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_response)

        btn_act_response.setOnClickListener {
            val response = MessageInfo(et_response.text.toString(), DateUtil.nowTime)
            val intent = Intent()
            intent.putExtra("message", response)
            //调用setResult方法表示携带应答参数返回到上一个页面
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

        val request = intent.extras.getParcelable<MessageInfo>("message")
        tv_response.text = "收到打包好的请求消息：\n请求时间为${request.send_time}\n请求内容为${request.content}"
    }
}
