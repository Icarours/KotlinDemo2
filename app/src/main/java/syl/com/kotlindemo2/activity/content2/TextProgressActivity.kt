package syl.com.kotlindemo2.activity.content2

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_text_progress.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import org.jetbrains.anko.selector
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean

/**
 * author   Bright
 * date     2019/6/2 14:57
 * desc
 * 水平进度条-文字
 */
class TextProgressActivity : BaseActivity() {
    private val progresses = listOf("0", "10", "20", "30", "40", "50", "60", "70", "80", "90", "100")

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_progress)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        sp_progress.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = progresses[0]
        tv_spinner.setOnClickListener {
            selector("请选择进度值", progresses) { dialog, i ->
                tv_spinner.text = progresses[i]
                val progress = progresses[i].toInt()
                tpb_progress.progress = progress
                //kotlin中成员变量有默认的set,get方法
                tpb_progress.progressText = "当前处理进度为$progress%"
            }
        }
    }
}
