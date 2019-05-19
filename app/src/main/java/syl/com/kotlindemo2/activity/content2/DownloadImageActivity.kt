package syl.com.kotlindemo2.activity.content2

import android.app.DownloadManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Handler
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_download_image.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import org.jetbrains.anko.selector
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean
import syl.com.kotlindemo2.util.downloader

/**
 * author   Bright
 * date     2019/5/19 20:15
 * desc
 * 下载图片
 */
class DownloadImageActivity : BaseActivity() {
    private var imagePath: String = ""
    private var downloadId: Long = 0
    private val imageNames = listOf(
        "洱海公园", "丹凤亭", "宛在堂", "满庭芳", "玉带桥",
        "眺望洱海", "洱海女儿", "海心亭", "洱海岸边", "烟波浩渺"
    )
    private val imageUrls = listOf(
        "http://b255.photo.store.qq.com/psb?/V11ZojBI0Zz6pV/nYJcslMIrGeDrujE5KZF2xBW8rjXMIVetZfrOAlSamM!/b/dPwxB5iaEQAA&bo=IANYAgAAAAABB1k!&rf=viewer_4",
        "http://b255.photo.store.qq.com/psb?/V11ZojBI0Zz6pV/Adcl9XVS.RBED4D8shjceYHOhhR*6mcNyCcq24kJG2k!/b/dPwxB5iYEQAA&bo=IANYAgAAAAABB1k!&rf=viewer_4",
        "http://b255.photo.store.qq.com/psb?/V11ZojBI0Zz6pV/bg*X6nT03YUReoJ97ked266WlWG3IzLjBdwHpKqkhYY!/b/dOg5CpjZEAAA&bo=IANYAgAAAAABB1k!&rf=viewer_4",
        "http://b255.photo.store.qq.com/psb?/V11ZojBI0Zz6pV/JOPAKl9BO1wragCEIVzXLlHwj83qVhb8uNuHdmVRwP4!/b/dPwxB5iSEQAA&bo=IANYAgAAAAABB1k!&rf=viewer_4",
        "http://b255.photo.store.qq.com/psb?/V11ZojBI0Zz6pV/7hHOgBEOBshH*7YAUx7RP0JzPuxRBD727mblw9TObhc!/b/dG4WB5i2EgAA&bo=IANYAgAAAAABB1k!&rf=viewer_4",
        "http://b255.photo.store.qq.com/psb?/V11ZojBI0Zz6pV/m4Rjx20D9iFL0D5emuYqMMDji*HGQ2w2BWqv0zK*tRk!/b/dGp**5dYEAAA&bo=IANYAgAAAAABB1k!&rf=viewer_4",
        "http://b255.photo.store.qq.com/psb?/V11ZojBI0Zz6pV/swfCMVl7Oefv8xgboV3OqkrahEs33KO7XwwH6hh7bnY!/b/dECE*5e9EgAA&bo=IANYAgAAAAABB1k!&rf=viewer_4",
        "http://b256.photo.store.qq.com/psb?/V11ZojBI0Zz6pV/tpRlB0oozaD9PyBtCmf3pQ5QY0keJJxYGX93I7n5NwQ!/b/dAyVmZiVEQAA&bo=IANYAgAAAAABB1k!&rf=viewer_4",
        "http://b256.photo.store.qq.com/psb?/V11ZojBI0Zz6pV/wMX2*LM6y.mBsFIYu8spAa7xXWUkPD.GHyazd.vMmYA!/b/dGYwoZjREQAA&bo=IANYAgAAAAABB1k!&rf=viewer_4",
        "http://b255.photo.store.qq.com/psb?/V11ZojBI0Zz6pV/2vl1n0KmKTPCv944MVJgLxKAhMiM*sqajIFQ43c*9DM!/b/dPaoCJhuEQAA&bo=IANYAgAAAAABB1k!&rf=viewer_4"
    )

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_image)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        sp_image_url.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = imageNames[0]
        tv_spinner.setOnClickListener {
            selector("请选择要下载的图片", imageNames) { dialog, i ->
                tv_spinner.text = imageNames[i]
                tv_spinner.isEnabled = false
                iv_image_url.setImageDrawable(null)
                tpc_progress.setProgress(0, 100f)
                tpc_progress.visibility = View.VISIBLE
                //声明下载任务的请求对象
                val down = DownloadManager.Request(Uri.parse(imageUrls[i]))
                //手机连上移动网络或者连上WIFI时均可进行下载操作
                down.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE or DownloadManager.Request.NETWORK_WIFI)
                //隐藏下载通知栏
                down.setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN)
                //指定不在系统的下载页面显示
                down.setVisibleInDownloadsUi(false)
                //指定下载文件在本地的保存路径
                down.setDestinationInExternalFilesDir(this, Environment.DIRECTORY_DCIM, "$i.jpg")
                //把下载请求添加到下载队列中
                //这里利用扩展属性实现了自动获取下载管理器实例
                //有关扩展属性的介绍参见第9章的“9.5.2 开始热身：震动器Vibrator”
                downloadId = downloader.enqueue(down)
                //启动下载进度的刷新任务
                handler.postDelayed(mRefresh, 100)
            }
        }
    }

    private val handler = Handler()
    private val mRefresh = object : Runnable {
        override fun run() {
            var isFinished = false
            val down_query = DownloadManager.Query()
            //根据编号来过滤下载任务
            down_query.setFilterById(downloadId)
            // 根据查询请求，获取符合条件的结果集游标
            val cursor = downloader.query(down_query)
            while (cursor.moveToNext()) {
                //获取下载文件的uri路径
                val uriIdx = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI)
                //获取文件的媒体类型
                val mediaTypeIdx = cursor.getColumnIndex(DownloadManager.COLUMN_MEDIA_TYPE)
                //获取文件的总大小
                val totalSizeIdx = cursor.getColumnIndex(DownloadManager.COLUMN_TOTAL_SIZE_BYTES)
                //获取已下载的文件大小
                val nowSizeIdx = cursor.getColumnIndex(DownloadManager.COLUMN_BYTES_DOWNLOADED_SO_FAR)
                //获取文件的下载状态
                val statusIdx = cursor.getColumnIndex(DownloadManager.COLUMN_STATUS)
                //计算当前的下载进度百分比
                val progress = (100 * cursor.getLong(nowSizeIdx) / cursor.getLong(totalSizeIdx)).toInt()
                if (cursor.getString(uriIdx) == null) {
                    break
                }
                //设置文本进度圈的当前进度
                tpc_progress.setProgress(progress, 50f)
                imagePath = cursor.getString(uriIdx)
                //下载进度达到100%，表示下载完成
                if (progress >= 100) {
                    isFinished = true
                }
                var statusDesc = if (isFinished) statusMap[DownloadManager.STATUS_SUCCESSFUL]
                else statusMap[cursor.getInt(statusIdx)]
                tv_image_result.text = "文件路径：${cursor.getString(uriIdx)}\n" +
                        "媒体类型：${cursor.getString(mediaTypeIdx)}\n" +
                        "文件总大小：${cursor.getLong(totalSizeIdx)}\n" +
                        "已下载大小：${cursor.getLong(nowSizeIdx)}\n" +
                        "下载进度：$progress%%\n" +
                        "下载状态：$statusDesc\n"
            }
            cursor.close()
            //尚未完成下载，继续轮询下载进度
            if (!isFinished) {
                handler.postDelayed(this, 100)
            } else {
                tv_spinner.isEnabled = true
                //下载完毕，隐藏圆圈进度，改为显示下载好的图片
                tpc_progress.visibility = View.INVISIBLE
                iv_image_url.setImageURI(Uri.parse(imagePath))
            }
        }
    }

    companion object {
        //下载状态类型与中文名称的映射关系定义
        private val statusMap = mapOf(
            Pair(DownloadManager.STATUS_PENDING, "挂起"),
            Pair(DownloadManager.STATUS_RUNNING, "运行中"),
            Pair(DownloadManager.STATUS_PAUSED, "暂停"),
            Pair(DownloadManager.STATUS_SUCCESSFUL, "成功"),
            Pair(DownloadManager.STATUS_FAILED, "失败")
        )
    }
}
