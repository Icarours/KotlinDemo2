package syl.com.kotlindemo2.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_act_request.*
import org.jetbrains.anko.startActivityForResult
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.MessageInfo
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.util.DateUtil

/**
 * author   Bright
 * date     2019/4/13 12:48
 * desc
 * 有返回数据
 */
class ActRequestActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_request)
        val titleBean = intent.getParcelableExtra("title") as TitleBean
        val mToolbar = findViewById<Toolbar>(R.id.toolbar)
        mToolbar.title = titleBean!!.title
        mToolbar.title = titleBean!!.title
        mToolbar.subtitle = titleBean!!.description
        initToolBar(mToolbar)

        btn_act_request.setOnClickListener {
            val info = MessageInfo(et_request.text.toString(), DateUtil.nowTime)
            //ForResult表示需要返回参数
            startActivityForResult<ActResponseActivity>(0, "message" to info)
        }
    }

    //从下个页面返回到本页面时回调onActivityResult方法
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (data != null) {
            //获得下一个页面的应答参数
            val response = data.extras.getParcelable<MessageInfo>("message")
            tv_request.text = "收到返回消息：\n应答时间为${response.send_time}\n应答内容为${response.content}"
        }
    }
}
