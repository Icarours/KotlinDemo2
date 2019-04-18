package syl.com.kotlindemo2.activity

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_scroll_flag.*
import org.jetbrains.anko.selector
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.adapter.RecyclerCollapseAdapter

/**
 * author   Bright
 * date     2019/4/18 22:41
 * desc
 * CoordinatorLayout+CollapsingToolbarLayout+ScrollFlag
 */
class ScrollFlagActivity : AppCompatActivity() {
    private val years = arrayOf("鼠年", "牛年", "虎年", "兔年", "龙年", "蛇年", "马年", "羊年", "猴年", "鸡年", "狗年", "猪年")
    private val descs = listOf(
        "scroll",
        "scroll|enterAlways",
        "scroll|exitUntilCollapsed",
        "scroll|enterAlways|enterAlwaysCollapsed",
        "scroll|snap"
    )
    private val flags = intArrayOf(
        AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL,
        AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS,
        AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_EXIT_UNTIL_COLLAPSED,
        AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS or AppBarLayout.LayoutParams.SCROLL_FLAG_ENTER_ALWAYS_COLLAPSED,
        AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or AppBarLayout.LayoutParams.SCROLL_FLAG_SNAP
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_scroll_flag)

        tl_title.setBackgroundColor(Color.YELLOW)
        setSupportActionBar(tl_title)
        ctl_title.title = "滚动标志"
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = RecyclerCollapseAdapter(this, years)

        //设置工具栏的主标题文本内容
        tl_title.title = "这是工具栏的主标题"
        //设置工具栏的主标题文本颜色
        tl_title.setTitleTextColor(Color.RED)
        //设置工具栏左边的Logo图标
        tl_title.setLogo(R.drawable.ic_launcher)
        //设置工具栏的副标题文本内容
        tl_title.subtitle = "这是副标题"
        //设置工具栏的副标题文本颜色
        tl_title.setSubtitleTextColor(Color.YELLOW)
        //设置工具栏的背景
//        tl_title.setBackgroundResource(R.color.blue_light)
        //使用Toolbar替换系统自带的ActionBar
//        setSupportActionBar(tl_title)
        //工具栏最左侧的导航图标，通常用作返回按钮
        tl_title.setNavigationIcon(R.drawable.ic_back)
        //最左侧导航图标的点击事件，即返回上一个页面
        //该方法必须放到setSupportActionBar之后，不然不起作用
        tl_title.setNavigationOnClickListener { finish() }

        initFlagSpinner()
    }

    private fun initFlagSpinner() {
        sp_flag.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = descs[0]
        tv_spinner.setOnClickListener {
            selector("请选择滚动标志", descs) { dialogInterface, i ->
                tv_spinner.text = descs[i]
                val params = ctl_title.layoutParams as AppBarLayout.LayoutParams
                params.scrollFlags = flags[i]
            }
        }
    }
}
