package syl.com.kotlindemo2.activity.content2

import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_image_write.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.util.DateUtil
import syl.com.kotlindemo2.util.FileUtil

/**
 * author   Bright
 * date     2019/6/1 15:54
 * desc
 * 写入图片文件
 */
class ImageWriteActivity : BaseActivity() {
    private val types = listOf("未婚", "已婚")
    private var bMarried = false
    private var mPath: String? = null
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_write)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        mPath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/"

        sp_married.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = types[0]
        tv_spinner.setOnClickListener {
            selector("请选择婚姻状况", types) { dialog,i ->
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
                    val bitmap = ll_info.drawingCache
                    val file_path = "$mPath${DateUtil.getFormatTime()}.png"
                    FileUtil.saveImage(file_path, bitmap)
                    tv_path.text = "用户注册信息图片的保存路径为：\n$file_path"
                    toast("图片已存入临时目录")
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        ll_info.isDrawingCacheEnabled = true
    }

    override fun onStop() {
        super.onStop()
        ll_info.isDrawingCacheEnabled = false
    }
}
