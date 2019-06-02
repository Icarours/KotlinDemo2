package syl.com.kotlindemo2.activity.content2

import android.os.Build
import android.os.Bundle
import android.os.Handler
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_progress_animation.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean

/**
 * author   Bright
 * date     2019/6/2 15:12
 * desc
 * 水平进度条动画
 */
class ProgressAnimationActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_animation)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        btn_animation.setOnClickListener {
            btn_animation.isEnabled = false
            //延迟50毫秒开始进度条动画
            handler.postDelayed(animation, 50)
        }
    }

    private var mProgress = 0
    private val handler = Handler()
    //定义一个刷新进度条的任务
    private val animation = object : Runnable {
        override fun run() {
            if (mProgress <= 100) {
                tpb_progress.progress = mProgress
                tpb_progress.progressText = "当前处理进度为$mProgress%"
                //当前进度未满100%，则继续进度刷新动画
                handler.postDelayed(this, 50)
                mProgress++
            } else {
                //进度条动画结束，恢复初始进度数值
                mProgress = 0
                btn_animation.isEnabled = true
            }
        }
    }
}
