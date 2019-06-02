package syl.com.kotlindemo2.activity.content2

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_menu_option.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.util.DateUtil

/**
 * author   Bright
 * date     2019/6/1 16:54
 * desc
 * ToolBar选项菜单
 */
class MenuOptionActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_option)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        //注意：如果当前页面继承自AppCompatActivity，并且appcompat版本不低于22.1.0
        //那么调用openOptionsMenu方法将不会弹出菜单。这应该是Android的一个bug
        btn_option.setOnClickListener { openOptionsMenu() }
        setRandomTime()
    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun initToolBar(mToolbar: Toolbar) {
        mToolbar.inflateMenu(R.menu.menu_option)
        mToolbar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.menu_change_time -> setRandomTime()
                R.id.menu_change_color -> tv_option.setTextColor(randomColor)
                R.id.menu_change_bg -> tv_option.setBackgroundColor(randomColor)
            }
            false
        }
        mToolbar.setNavigationOnClickListener { finish() }
    }

    private fun setRandomTime() {
        val desc = "${DateUtil.nowDateTime} 这里是菜单显示文本"
        tv_option.text = desc
    }

    private val mColorArray = intArrayOf(
        Color.BLACK,
        Color.WHITE,
        Color.RED,
        Color.YELLOW,
        Color.GREEN,
        Color.BLUE,
        Color.CYAN,
        Color.MAGENTA,
        Color.GRAY,
        Color.DKGRAY
    )
    private val randomColor: Int
        get() {
            val random = (Math.random() * 10 % 10).toInt()
            return mColorArray[random]
        }
}
