package syl.com.kotlindemo2.activity.content2

import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_text_read.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean
import java.io.File

/**
 * author   Bright
 * date     2019/5/25 17:27
 * desc
 * 文件读取-TextWrite
 */
class TextReadActivity : BaseActivity() {
    private lateinit var mPath: String
    private var fileNames: MutableList<String> = mutableListOf()
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_read)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        sp_file.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        btn_delete.setOnClickListener {
            fileNames.forEach {
                //循环删除文本文件
                val file = File(mPath + it)
                if (!file.delete()) {
                    Log.d(TAG, "file_path=$it, delete failed")
                }
            }
            refreshSpinner()
            toast("已删除临时目录下的所有文本文件")
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
            .filter { it.extension == "txt" } //选择扩展名为txt的文本文件
            .forEach { fileNames.add(it.name) } //循环处理符合条件的文件,将文件名添加到fileNames集合中
        if (!fileNames.isEmpty()) {
            tv_spinner.text = fileNames[0]
            tv_spinner.setOnClickListener {
                selector("请选择文本文件", fileNames) { dialog, i ->
                    tv_spinner.text = fileNames[i]
                    val file_path = mPath + fileNames[i]
                    //readText读取文本形式的文件内容，readLines按行读取文件内容
                    val content = File(file_path).readText()
                    tv_text.text = "文件内容如下：\n$content"
                }
            }
        } else {
            tv_spinner.text = ""
            tv_spinner.setOnClickListener { }
            tv_text.text = ""
        }
    }

    companion object {
        private val TAG = "TextReadActivity"
    }
}
