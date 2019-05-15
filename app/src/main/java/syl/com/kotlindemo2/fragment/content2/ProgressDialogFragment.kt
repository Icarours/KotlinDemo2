package syl.com.kotlindemo2.fragment.content2

import android.app.ProgressDialog
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_progress_dialog.*
import kotlinx.android.synthetic.main.fragment_progress_dialog.view.*
import org.jetbrains.anko.support.v4.indeterminateProgressDialog
import org.jetbrains.anko.support.v4.progressDialog
import org.jetbrains.anko.support.v4.selector
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment
import syl.com.kotlindemo2.util.DateUtil

/**
 * Created by Bright on 2019/5/11.
 * @Describe
 * 圆形对话框进度条,水平对话框进度条
 * @Called
 */
class ProgressDialogFragment : BaseFragment() {
    private val progressNames = listOf("圆圈进度", "水平进度条")
    private val progressStyles = intArrayOf(ProgressDialog.STYLE_SPINNER, ProgressDialog.STYLE_HORIZONTAL)
    private var dialog: ProgressDialog? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_progress_dialog, container, false).apply {
            sp_style.visibility = View.GONE
            tv_spinner.visibility = View.VISIBLE
            tv_spinner.text = progressNames[0]
            tv_spinner.setOnClickListener {
                selector("请选择对话框样式", progressNames) { dialogInteface, i ->
                    tv_spinner.text = progressNames[i]
                    if (dialog == null || !dialog!!.isShowing) {
                        if (progressStyles[i] == ProgressDialog.STYLE_SPINNER) {
                            dialog = indeterminateProgressDialog("正在努力加载页面", "请稍候")
                            dialog!!.setCanceledOnTouchOutside(false)//点击dialog空白部分,dialog不消失
                            dialog!!.show()
                            handler.postDelayed(closeDialog, 5000)
                        } else {
                            dialog = progressDialog("正在努力加载页面", "请稍候")
                            dialog!!.setCanceledOnTouchOutside(false)//点击dialog空白部分,dialog不消失
                            dialog!!.progress = 0
                            dialog!!.show()
                            RefreshThread().start()
                        }
                    }
                }
            }
        }
    }

    private val closeDialog = Runnable {
        if (dialog!!.isShowing) {
            dialog!!.dismiss()
            tv_result.text = "${DateUtil.nowTime}  ${tv_spinner.text}加载完成"
        }
    }

    private inner class RefreshThread : Thread() {
        override fun run() {
            for (i in 0..100) {
                val message = Message.obtain()
                message.what = 0
                message.arg1 = i
                handler.sendMessage(message)
                Thread.sleep(200)
            }
            handler.sendEmptyMessage(1)
        }
    }

    private val handler = object : Handler() {
        override fun handleMessage(msg: Message) {
            when (msg.what) {
                0 -> dialog!!.progress = msg.arg1
                else -> post(closeDialog)
            }
        }
    }
}