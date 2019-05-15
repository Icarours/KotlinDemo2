package syl.com.kotlindemo2.fragment.content2

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_message.*
import kotlinx.android.synthetic.main.fragment_message.view.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment
import syl.com.kotlindemo2.util.DateUtil

/**
 * Created by Bright on 2019/5/11.
 * @Describe
 * handler消息传递,子线程的用法
 *
 * 如果子线程,Handler中有刷新UI的操作,一定要在离开当前页面的时候停止线程,停止发送消息刷新UI
 * @Called
 */
class MessageFragment : BaseFragment() {
    private var isPlaying = false
    private val BEGIN = 0 //开始播放新闻
    private val SCROLL = 1 //持续滚动新闻
    private val END = 2 //结束播放新闻
    private val VIEW_GONE = 3 //离开当前页面
    private var CURRENT_STATE = -1 //当前状态,做个标记,如果当前状态是3,就停止发送消息,更新页面
    private val news = arrayOf(
        "北斗三号卫星发射成功，定位精度媲美GPS",
        "美国赌城拉斯维加斯发生重大枪击事件",
        "日本在越南承建的跨海大桥未建完已下沉",
        "南水北调功在当代，数亿人喝上长江水",
        "马克龙呼吁重建可与中国匹敌的强大欧洲"
    )
    //自定义的处理器类，区分三种消息类型，给tv_message显示不同的文本内容
    private val mHandler = object : Handler() {
        override fun handleMessage(msg: Message) {
            if (CURRENT_STATE == VIEW_GONE) {
                //如果当前状态是3,就停止发送消息,更新页面
                Log.d("MessageFragment", "离开页面")
            } else {
                val desc = tv_message.text.toString()
                tv_message.text = when (msg.what) {
                    BEGIN -> "$desc\n${DateUtil.nowDateTime} 下面开始播放新闻"
                    SCROLL -> "$desc\n${DateUtil.nowDateTime} ${msg.obj}"
                    else -> "$desc\n${DateUtil.nowDateTime} 新闻播放结束，谢谢观看"
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_message, container, false).apply {

            //指定文本视图内部文本的对齐方式为靠左且靠右对齐
            tv_message.gravity = Gravity.LEFT or Gravity.BOTTOM
            //指定文本视图的显示行数为8行
            tv_message.setLines(8)
            //指定文本视图的最大行数为8行
            tv_message.maxLines = 8
            //指定文本视图内部文本的移动方式为滚动
            tv_message.movementMethod = ScrollingMovementMethod()
            btn_start_message.setOnClickListener {
                if (!isPlaying) {
                    isPlaying = true
                    //线程第一种写法的调用方式，通过具体的线程类进行构造。
                    //注意每个线程实例只能启动一次，不能重复启动。
                    //若要多次执行该线程的任务，则需每次都构造新的线程实例。
                    //PlayThread().start()
                    //线程的第二种写法，采用匿名实例的形式。第二种写法无需显式构造
                    if (CURRENT_STATE == VIEW_GONE) {
                        //如果当前状态是3,就停止发送消息,更新页面
                        Log.d("MessageFragment", "离开页面")
                    } else {
                        Thread {
                            //发送“开始播放新闻”的消息类型
                            mHandler.sendEmptyMessage(BEGIN)
                            while (isPlaying) {
                                //休眠两秒，模拟获取突发新闻的网络延迟
                                Thread.sleep(2000)
                                //调用Message的obtain方法，获得一个消息实例
                                val message = Message.obtain()
                                message.what = SCROLL
                                message.obj = news[(Math.random() * 30 % 5).toInt()]
                                //发送“持续滚动新闻”的消息类型
                                mHandler.sendMessage(message)
                            }
                            isPlaying = true
                            Thread.sleep(2000)
                            //发送“结束播放新闻”的消息类型
                            mHandler.sendEmptyMessage(END)
                            isPlaying = false
                        }.start()
                    }
                }
            }
            btn_stop_message.setOnClickListener { isPlaying = false }
        }
    }

    //线程的第一种写法，继承Thread类并重载run方法
    private inner class PlayThread : Thread() {
        override fun run() {
            mHandler.sendEmptyMessage(BEGIN)
            while (isPlaying) {
                Thread.sleep(2000)
                val message = Message.obtain()
                message.what = SCROLL
                message.obj = news[(Math.random() * 30 % 5).toInt()]
                mHandler.sendMessage(message)
            }
            isPlaying = true
            Thread.sleep(2000)
            mHandler.sendEmptyMessage(END)
            isPlaying = false
        }
    }

    override fun onPause() {
        super.onPause()

        mHandler.sendEmptyMessage(VIEW_GONE)
        CURRENT_STATE = VIEW_GONE
        isPlaying = false
    }
}