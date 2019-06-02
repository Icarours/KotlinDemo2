package syl.com.kotlindemo2.activity.content2

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_app_read.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.base.MyApplication
import syl.com.kotlindemo2.bean.TitleBean

/**
 * author   Bright
 * date     2019/6/1 16:37
 * desc
 * 从全局变量MyApplication读取信息
 */
class AppReadActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_read)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        readAppMemory()
    }

    private fun readAppMemory() {
        var desc = "全局内存中保存的信息如下："
        val mapParam = MyApplication.instance().mInfoMap
        for (item_map in mapParam) {
            desc = "$desc\n　${item_map.key}的取值为${item_map.value}"
        }
        if (mapParam.isEmpty()) {
            desc = "全局内存中保存的信息为空"
        }
        tv_app.text = desc
    }
}
