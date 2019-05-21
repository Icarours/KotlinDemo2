package syl.com.kotlindemo2.activity.content2

import android.graphics.Color
import android.graphics.Typeface
import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.style.*
import android.view.View
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_spannable.*
import kotlinx.android.synthetic.main.simple_tool_bar.*
import org.jetbrains.anko.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseActivity
import syl.com.kotlindemo2.bean.TitleBean

/**
 * author   Bright
 * date     2019/5/21 22:16
 * desc
 * 可变字符串
 */
class SpannableActivity : BaseActivity() {
    private val spannables = listOf("增大字号", "加粗字体", "前景红色", "背景绿色", "下划线", "表情图片", "Anko自定义")
    private val text = "为人民服务"
    private val key = "人民"
    private var beginPos = text.indexOf(key)
    private var endPos = beginPos + key.length
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_spannable)

        val titleBean = intent.getParcelableExtra("title") as TitleBean
        toolbar.title = titleBean.title
        toolbar.subtitle = titleBean.description
        initToolBar(toolbar)

        tv_spannable.text = text

        sp_spannable.visibility = View.GONE
        tv_spinner.visibility = View.VISIBLE
        tv_spinner.text = spannables[0]
        tv_spinner.setOnClickListener {
            selector("请选择可变字符串样式", spannables) { dialog,i ->
                tv_spinner.text = spannables[i]
                val spanText = SpannableString(text)
                //对这段文本运用指定的风格样式
                spanText.setSpan(when (i) {
                    0 -> RelativeSizeSpan(1.5f) //文字大小增大到1.5倍大
                    1 -> StyleSpan(Typeface.BOLD) //文字字体使用粗体
                    2 -> ForegroundColorSpan(Color.RED) //文字颜色使用红色
                    3 -> BackgroundColorSpan(Color.GREEN) //背景色使用绿色
                    4 -> UnderlineSpan() //文字下方增加下划线
                    else -> ImageSpan(this@SpannableActivity, R.drawable.people) //把文字替换为图片
                }, beginPos, endPos, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                tv_spannable.text = spanText
                if (i >= 6) {
                    //使用Anko库的buildSpanned函数连续构建可变字符串
                    tv_spannable.text = buildSpanned {
                        append("为", Bold)
                        append("人民", RelativeSizeSpan(1.5f))
                        append("服务", foregroundColor(Color.RED))
                        append("是谁", backgroundColor(Color.GREEN))
                        append("提出来的", Underline)
                    }
                }
            }
        }
    }
}
