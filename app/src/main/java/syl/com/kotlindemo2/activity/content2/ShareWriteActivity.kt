package syl.com.kotlindemo2.activity.content2

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_share_write.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.util.DateUtil
import syl.com.kotlindemo2.util.Preference

/**
 * author   Bright
 * date     2019/5/21 22:38
 * desc
 * SharedPreference
 */
class ShareWriteActivity : BaseActivity() {
    private val types = listOf("未婚", "已婚")
    private var bMarried = false
    private var name: String by Preference(this, "name", "")
    private var age: Int by Preference(this, "age", 0)
    private var height: Long by Preference(this, "height", 0)
    private var weight: Float by Preference(this, "weight", 0f)
    private var married: Boolean by Preference(this, "married", false)
    private var update_time: String by Preference(this, "update_time", "")

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_write)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        sp_married.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = types[0]
        tv_spinner.setOnClickListener {
            selector("请选择婚姻状况", types) { dialog, i ->
                tv_spinner.text = types[i]
                bMarried = if (i == 0) false else true
            }
        }
        btn_save.setOnClickListener {
            when {
                et_name.text.isEmpty() -> toast("请先填写姓名")
                et_age.text.isEmpty() -> toast("请先填写年龄")
                et_height.text.isEmpty() -> toast("请先填写身高")
                et_weight.text.isEmpty() -> toast("请先填写体重")
                else -> {
                    name = et_name.text.toString()
                    age = et_age.text.toString().toInt()
                    height = et_height.text.toString().toLong()
                    weight = et_weight.text.toString().toFloat()
                    married = bMarried
                    update_time = DateUtil.nowDateTime
                    toast("数据已写入共享参数")
                    Log.d(
                        "ShareWriteActivity",
                        "name=${name}--age=${age}--height=${height}--weight=${weight}--married=${married}--update_time=${update_time}"
                    )
                }
            }
        }
    }
}
