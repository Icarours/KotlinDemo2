package syl.com.kotlindemo2.activity

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_button_click.*
import org.jetbrains.anko.longToast
import org.jetbrains.anko.toast
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean

/**
 * author   Bright
 * date     2019/4/12 7:40
 * desc
 * Fragment中apply方法貌似不能直接实现OnClickListener接口,只能使用匿名内部类,或者直接创建一个子类
 */
class ButtonClickActivity : BaseActivity(), View.OnClickListener, View.OnLongClickListener {
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_button_click)
        val titleBean = intent.getParcelableExtra("title") as TitleBean
        val mToolbar = findViewById<Toolbar>(R.id.toolbar)
        mToolbar.title = titleBean!!.title
        mToolbar.title = titleBean!!.title
        mToolbar.subtitle = titleBean!!.description
        initToolBar(mToolbar)
        //点击事件第一种：匿名函数方式
        btn_click_anonymos.setOnClickListener { v ->
            //Kotlin对变量进行类型转换的关键字是as
            toast("您点击了控件：${(v as Button).text}")
        }
        btn_click_anonymos.setOnLongClickListener { v ->
            //Kotlin对变量进行类型转换的关键字是as
            longToast("您长按了控件：${(v as Button).text}")
            true
        }
        //点击事件第二种：内部类方式
        btn_click_inner.setOnClickListener(MyClickListener())
        btn_click_inner.setOnLongClickListener(MyLongClickListener())
        //点击事件第三种：Activity实现接口
        btn_click_interface.setOnClickListener(this)
        btn_click_interface.setOnLongClickListener(this)
    }

    //点击事件第三种：Activity实现接口
    override fun onClick(v: View) {
        if (v.id == R.id.btn_click_interface) {
            toast("您点击了控件：${(v as Button).text}")
        }
    }

    override fun onLongClick(v: View): Boolean {
        if (v.id == R.id.btn_click_interface) {
            longToast("您长按了控件：${(v as Button).text}")
        }
        return true
    }

    //点击事件第四种：XML布局中指定函数
    fun onButtonClick(v: View) {
        toast("您点击了控件：${(v as Button).text}")
    }

    //点击事件第二种：内部类方式
    private inner class MyClickListener : View.OnClickListener {
        override fun onClick(v: View) {
            toast("您点击了控件：${(v as Button).text}")
        }
    }

    private inner class MyLongClickListener : View.OnLongClickListener {
        override fun onLongClick(v: View): Boolean {
            longToast("您长按了控件：${(v as Button).text}")
            return true
        }
    }
}
