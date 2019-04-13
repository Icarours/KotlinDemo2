package syl.com.kotlindemo2.activity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_act_first.*
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.newTask
import org.jetbrains.anko.startActivity
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.util.DateUtil

/**
 * author   Bright
 * date     2019/4/13 11:46
 * desc
 * Activity跳转->无数据
 */
class ActFirstActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_act_first)
        val titleBean = intent.getParcelableExtra("title") as TitleBean
        val mToolbar = findViewById<Toolbar>(R.id.toolbar)
        mToolbar.title = titleBean!!.title
        mToolbar.title = titleBean!!.title
        mToolbar.subtitle = titleBean!!.description
        initToolBar(mToolbar)

        btn_act_request.setOnClickListener {
            //第一种写法，参数名和参数值使用关键字to隔开
            startActivity<ActSecondActivity>(
                "request_time" to DateUtil.nowTime,
                "request_content" to et_request.text.toString())
            //第二种写法，利用Pair把参数名和参数值进行配对
//            startActivity<ActSecondActivity>(
//                    Pair("request_time", DateUtil.nowTime),
//                    Pair("request_content", et_request.text.toString()))
        }

        btn_act_flag.setOnClickListener {
            val intent = intentFor<ActSecondActivity>(
                "request_time" to DateUtil.nowTime,
                "request_content" to et_request.text.toString())
            startActivity(intent.newTask() //对应启动标志FLAG_ACTIVITY_NEW_TASK
                //.singleTop() //对应启动标志FLAG_ACTIVITY_SINGLE_TOP
                //.clearTop() //对应启动标志FLAG_ACTIVITY_CLEAR_TOP
                //.noHistory() //对应启动标志FLAG_ACTIVITY_NO_HISTORY
                //.clearTask() //对应启动标志FLAG_ACTIVITY_CLEAR_TASK
            )
        }
    }
}
