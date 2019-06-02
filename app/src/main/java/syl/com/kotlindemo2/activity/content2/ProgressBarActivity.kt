package syl.com.kotlindemo2.activity.content2

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_progress_bar.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean
/**
 * author   Bright
 * date     2019/6/2 14:32
 * desc
 * 水平进度条
 */
class ProgressBarActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_progress_bar)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        //设置最大进度
        pb_progress.max = 100
        //设置默认进度
        pb_progress.progress = 0
        //设置进度条图形
        pb_progress.progressDrawable = resources.getDrawable(R.drawable.progress_green)
        btn_progress.setOnClickListener {
            //根据输入的进度数值，展示进度条的当前进度
            pb_progress.progress = et_progress.text.toString().toInt()
        }
    }
}
