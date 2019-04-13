package syl.com.kotlindemo2.activity

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_parcelable_first.*
import org.jetbrains.anko.startActivity
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.MessageInfo
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.util.DateUtil

/**
 * author   Bright
 * date     2019/4/13 12:38
 * desc
 * 请求打包数据
 */
class ParcelableFirstActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_parcelable_first)
        val titleBean = intent.getParcelableExtra("title") as TitleBean
        val mToolbar = findViewById<Toolbar>(R.id.toolbar)
        mToolbar.title = titleBean!!.title
        mToolbar.title = titleBean!!.title
        mToolbar.subtitle = titleBean!!.description
        initToolBar(mToolbar)

        btn_act_request.setOnClickListener {
            val request = MessageInfo(et_request.text.toString(), DateUtil.nowTime)
            startActivity<ParcelableSecondActivity>("message" to request)
        }
    }
}
