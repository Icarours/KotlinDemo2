package syl.com.kotlindemo2.activity.content2

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_share_read.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.util.Preference

/**
 * author   Bright
 * date     2019/5/21 22:53
 * desc
 * 读取SharedPreference
 */
class ShareReadActivity : BaseActivity() {
    private var name: String by Preference(this, "name", "")
    private var age: Int by Preference(this, "age", 0)
    private var height: Long by Preference(this, "height", 0)
    private var weight: Float by Preference(this, "weight", 0f)
    private var married: Boolean by Preference(this, "married", false)
    private var update_time: String by Preference(this, "update_time", "")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_share_read)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        val desc = "共享参数中保存的信息如下：" +
                "\n　name的取值为$name" +
                "\n　age的取值为$age" +
                "\n　height的取值为$height" +
                "\n　weight的取值为$weight" +
                "\n　married的取值为$married" +
                "\n　update_time的取值为$update_time"
        tv_share.text = desc
    }
}
