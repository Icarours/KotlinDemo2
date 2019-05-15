package syl.com.kotlindemo2.activity.content1

import android.graphics.Color
import android.os.Bundle
import android.view.Window
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_app_bar_recycler.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.adapter.RecyclerCollapseAdapter
/**
 * author   Bright
 * date     2019/4/18 21:35
 * desc
 * CoordinatorLayout+AppBarLayout
 */
class AppBarRecyclerActivity : AppCompatActivity() {
    private val yearArray = arrayOf("鼠年", "牛年", "虎年", "兔年", "龙年", "蛇年", "马年", "羊年", "猴年", "鸡年", "狗年", "猪年")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.activity_app_bar_recycler)
        setSupportActionBar(tl_title)
        
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
        tl_title.setBackgroundResource(R.color.blue_light)
        //使用Toolbar替换系统自带的ActionBar
        setSupportActionBar(tl_title)
        //工具栏最左侧的导航图标，通常用作返回按钮
        tl_title.setNavigationIcon(R.drawable.ic_back)
        //最左侧导航图标的点击事件，即返回上一个页面
        //该方法必须放到setSupportActionBar之后，不然不起作用
        tl_title.setNavigationOnClickListener { finish() }
        
        rv_main.layoutManager = LinearLayoutManager(this)
        rv_main.adapter = RecyclerCollapseAdapter(this, yearArray)
    }
}
