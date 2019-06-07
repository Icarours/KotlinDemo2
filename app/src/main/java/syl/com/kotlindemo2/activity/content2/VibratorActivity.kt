package syl.com.kotlindemo2.activity.content2

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_vibrator.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import org.jetbrains.anko.selector
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.util.DateUtil
import syl.com.kotlindemo2.util.vibrator

/**
 * author   Bright
 * date     2019/6/7 9:04
 * desc
 * 振动器
 */
class VibratorActivity : BaseActivity() {
    private val durations = listOf(500, 1000, 2000, 3000, 4000, 5000)
    private val descs = listOf("0.5秒", "1秒", "2秒", "3秒", "4秒", "5秒")
    private var interval: Int = 500
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vibrator)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        btn_start.setOnClickListener {
            //常规做法：从系统服务中获取震动器对象
//            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
//            vibrator.vibrate(3000)
            //利用扩展函数获得震动器对象
//            getVibrator().vibrate(interval.toLong())
            //利用扩展函数实现扩展属性，直接使用vibrator即可指代震动器对象
            vibrator.vibrate(interval.toLong())
            tv_vibrator.text = "${DateUtil.nowTime} 手机震动了${interval / 1000.0f}秒"
        }

        sp_duration.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = descs[0]
        tv_spinner.setOnClickListener {
            selector("请选择震动时长", descs) { dialog, i ->
                tv_spinner.text = descs[i]
                interval = durations[i]
            }
        }
    }
}
