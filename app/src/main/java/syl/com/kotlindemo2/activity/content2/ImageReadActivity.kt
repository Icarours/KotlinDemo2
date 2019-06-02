package syl.com.kotlindemo2.activity.content2

import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_image_read.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean
import java.io.File

/**
 * author   Bright
 * date     2019/6/1 16:12
 * desc
 * 读取图片文件
 */
class ImageReadActivity : BaseActivity() {
    private lateinit var mPath: String
    private var fileNames: MutableList<String> = mutableListOf()
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_read)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        sp_file.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        btn_delete.setOnClickListener {
            fileNames.forEach {
                //循环删除图片文件
                val file = File(mPath + it)
                if (!file.delete()) {
                    Log.d(TAG, "file_path=$it, delete failed")
                }
            }
            refreshSpinner()
            toast("已删除临时目录下的所有图片文件")
        }
        mPath = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString() + "/"
        refreshSpinner()
    }

    private fun refreshSpinner() {
        fileNames.clear()
        //在该目录下走一圈，得到文件目录树结构
        val fileTree: FileTreeWalk = File(mPath).walk()
        fileTree.maxDepth(1) //需遍历的目录层级为1，即无需检查子目录
            .filter { it.isFile } //只挑选文件，不处理文件夹
            .filter { it.extension in listOf("png", "jpg") } //选择扩展名为png和jpg的图片文件
            .forEach { fileNames.add(it.name) } //循环处理符合条件的文件
        if (!fileNames.isEmpty()) {
            tv_spinner.text = fileNames[0]
            tv_spinner.setOnClickListener {
                selector("请选择图片文件", fileNames) { dialog, i ->
                    tv_spinner.text = fileNames[i]
                    val file_path = mPath + fileNames[i]
                    ////方式一：利用字节数组读取位图
                    ////readBytes读取字节数组形式的文件内容
                    //val bytes = File(file_path).readBytes()
                    ////decodeByteArray从字节数组解析图片
                    //val bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                    ////方式二：利用输入流读取位图
                    ////inputStream获取文件的输入流对象
                    //val fis = File(file_path).inputStream()
                    ////decodeStream从输入流解析图片
                    //val bitmap = BitmapFactory.decodeStream(fis)
                    //fis.close()
                    //方式三：直接从文件路径获取位图
                    //decodeFile从指定路径解析图片
                    val bitmap = BitmapFactory.decodeFile(file_path)
                    iv_image.setImageBitmap(bitmap)
                }
            }
        } else {
            tv_spinner.text = ""
            tv_spinner.setOnClickListener { }
            iv_image.setImageDrawable(null)
        }
    }

    companion object {
        private val TAG = "ImageReadActivity"
    }
}
