package syl.com.kotlindemo2.activity.content2

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_app_write.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.base.MyApplication
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.util.DateUtil

/**
 * author   Bright
 * date     2019/6/1 16:23
 * desc
 * 保存信息到全局变量-MyApplication
 */
class AppWriteActivity : BaseActivity() {
    private val types = listOf("未婚", "已婚")
    private var bMarried = false
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_write)

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
                    val app = MyApplication.instance()
                    app.mInfoMap.put("name", et_name.text.toString())
                    app.mInfoMap.put("age", et_age.text.toString())
                    app.mInfoMap.put("height", et_height.text.toString())
                    app.mInfoMap.put("weight", et_weight.text.toString())
                    app.mInfoMap.put("married", types[if (!bMarried) 0 else 1])
                    app.mInfoMap.put("update_time", DateUtil.nowDateTime)
                    toast("数据已写入全局内存")
                    Log.d(TAG, "数据已写入全局内存")
                }
            }
        }
    }

    companion object {
        private var TAG = "AppWriteActivity"
    }
}
