package syl.com.kotlindemo2.fragment.content1

import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_kotlin_text_marquee.view.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment

/**
 * Created by Bright on 2019/4/13.
 * @Describe TextView跑马灯效果
 * @Called
 */
class KotlinTextMarqueeFragment : BaseFragment() {
    private var bPause = false
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_text_marquee, container, false).apply {
            tv_marquee.text = "快讯：红色预警，超强台风“泰利”即将登陆，请居民关紧门窗、备足粮油，做好防汛救灾准备！"
            tv_marquee.textSize = 17f
            tv_marquee.setTextColor(Color.BLACK)
            tv_marquee.setBackgroundColor(Color.WHITE)
            tv_marquee.gravity = Gravity.LEFT or Gravity.CENTER //左对齐且垂直居中
            tv_marquee.ellipsize = TextUtils.TruncateAt.MARQUEE //从右向左滚动的跑马灯
            tv_marquee.setSingleLine(true) //跑马灯效果务必设置SingleLine
            tv_marquee.requestFocus() //强制获得焦点，让跑马灯滚起来,刚出现就跑起来
            tv_marquee.setOnClickListener {
                bPause = !bPause
                tv_marquee.isFocusable = !bPause
                tv_marquee.isFocusableInTouchMode = !bPause
                if (!bPause) {
                    tv_marquee.requestFocus() //强制获得焦点，让跑马灯滚起来
                }
            }
        }
    }
}