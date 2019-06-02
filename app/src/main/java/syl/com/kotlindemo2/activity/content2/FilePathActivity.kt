package syl.com.kotlindemo2.activity.content2

import android.os.Build
import android.os.Bundle
import android.os.Environment
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_file_path.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean

/**
 * author   Bright
 * date     2019/5/25 17:07
 * desc
 * 文件保存位置
 */
class FilePathActivity : BaseActivity() {

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_file_path)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        //获取系统的公共存储路径
        val publicPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        //获取当前App的私有存储路径
        val privatePath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString();
        tv_file_path.text = "系统的公共存储路径位于${publicPath}" +
                "\n\n当前App的私有存储路径位于${privatePath}" +
                "\n\nAndroid7.0之后默认禁止访问公共存储目录"
    }
}
