package syl.com.kotlindemo2.fragment.content1

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.fragment_kotlin_relative_layout.*
import kotlinx.android.synthetic.main.fragment_kotlin_relative_layout.view.*
import org.jetbrains.anko.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment

/**
 * Created by Bright on 2019/4/12.
 * @Describe RelativeLayout
 *
 *
 * 代码里的方位类型有多种取值，比如RelativeLayout.LEFT_OF表示位于指定视图的左边，RelativeLayout.ALIGN_RIGHT表示与指定视图右侧对齐，
 * RelativeLayout.CENTER_IN_PARENT表示位于上级视图中央等等。除了above和alignParentLeft之外，Anko也提供了所有的相对位置设定方法，具体的对应关系说明见下表。
Anko库的相对位置    RelativeLayout类的相对位置
leftOf    LEFT_OF
sameTop    ALIGN_TOP
above    ABOVE
sameLeft    ALIGN_LEFT
rightOf    RIGHT_OF    
sameBottom    ALIGN_BOTTOM
below    BELOW
sameRight    ALIGN_RIGHT
centerInParent    CENTER_IN_PARENT
alignParentLeft    ALIGN_PARENT_LEFT
centerVertically    CENTER_VERTICAL
alignParentTop    ALIGN_PARENT_TOP
centerHorizontally    CENTER_HORIZONTAL
alignParentRight    ALIGN_PARENT_RIGHT
alignParentBottom    ALIGN_PARENT_BOTTOM
---------------------
作者：湖前琴亭
来源：CSDN
原文：https://blog.csdn.net/aqi00/article/details/82829481
版权声明：本文为博主原创文章，转载请附上博文链接！
 * @Called
 */
class KotlinRelativeLayoutFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_relative_layout, container, false).apply {
            tv_tips.text="代码里的方位类型有多种取值，比如RelativeLayout.LEFT_OF表示位于指定视图的左边，RelativeLayout.ALIGN_RIGHT" +
                    "表示与指定视图右侧对齐，RelativeLayout.CENTER_IN_PARENT表示位于上级视图中央等等。" +
                    "除了above和alignParentLeft之外，Anko也提供了所有的相对位置设定方法，具体的对应关系说明见下表。\n" +
                    "Anko库的相对位置    RelativeLayout类的相对位置\n" +
                    "leftOf    LEFT_OF\n" +
                    "sameTop    ALIGN_TOP\n" +
                    "above    ABOVE\n" +
                    "sameLeft    ALIGN_LEFT\n" +
                    "rightOf    RIGHT_OF    \n" +
                    "sameBottom    ALIGN_BOTTOM\n" +
                    "below    BELOW\n" +
                    "sameRight    ALIGN_RIGHT\n" +
                    "centerInParent    CENTER_IN_PARENT\n" +
                    "alignParentLeft    ALIGN_PARENT_LEFT\n" +
                    "centerVertically    CENTER_VERTICAL\n" +
                    "alignParentTop    ALIGN_PARENT_TOP\n" +
                    "centerHorizontally    CENTER_HORIZONTAL\n" +
                    "alignParentRight    ALIGN_PARENT_RIGHT\n" +
                    "alignParentBottom    ALIGN_PARENT_BOTTOM"
            btn_add_left.setOnClickListener { v ->
                val rl_params = RelativeLayout.LayoutParams(dip(50), dip(50))
                rl_params.leftOf(v)
                rl_params.sameTop(v)
                addNewView(rl_params)
            }
            btn_add_above.setOnClickListener { v ->
                val rl_params = RelativeLayout.LayoutParams(dip(50), dip(50))
                rl_params.above(v.id)
                rl_params.sameLeft(v.id)
                addNewView(rl_params)
            }
            btn_add_right.setOnClickListener { v ->
                val rl_params = RelativeLayout.LayoutParams(dip(50), dip(50))
                rl_params.rightOf(v)
                rl_params.sameBottom(v)
                addNewView(rl_params)
            }
            btn_add_below.setOnClickListener { v ->
                val rl_params = RelativeLayout.LayoutParams(dip(50), dip(50))
                rl_params.below(v)
                rl_params.sameRight(v)
                addNewView(rl_params)
            }
            btn_add_center.setOnClickListener { v ->
                val rl_params = RelativeLayout.LayoutParams(dip(50), dip(50))
                rl_params.centerInParent()
                addNewView(rl_params)
            }
            btn_add_parent_left.setOnClickListener { v ->
                val rl_params = RelativeLayout.LayoutParams(dip(50), dip(50))
                rl_params.alignParentLeft()
                rl_params.centerVertically()
                addNewView(rl_params)
            }
            btn_add_parent_top.setOnClickListener { v ->
                val rl_params = RelativeLayout.LayoutParams(dip(50), dip(50))
                rl_params.alignParentTop()
                rl_params.centerHorizontally()
                addNewView(rl_params)
            }
            btn_add_parent_right.setOnClickListener { v ->
                val rl_params = RelativeLayout.LayoutParams(dip(50), dip(50))
                rl_params.alignParentRight()
                addNewView(rl_params)
            }
            btn_add_parent_bottom.setOnClickListener { v ->
                val rl_params = RelativeLayout.LayoutParams(dip(50), dip(50))
                rl_params.alignParentBottom()
                addNewView(rl_params)
            }
        }
    }

    private fun addNewView(rl_params: RelativeLayout.LayoutParams) {
        var v = View(context)
        v.setBackgroundColor(Color.GREEN)
        v.layoutParams = rl_params
        v.setOnLongClickListener {
            rl_content.removeView(it)
            true
        }
        rl_content.addView(v)
    }

    //根据参照物与方位类型添加下级视图
    private fun addNewView(align: Int, referId: Int) {
        var v = View(context)
        v.setBackgroundColor(Color.GREEN)
        val rl_params = RelativeLayout.LayoutParams(100, 100)
        rl_params.addRule(align, referId)
        v.layoutParams = rl_params
        v.setOnLongClickListener { vv -> rl_content.removeView(vv); true }
        rl_content.addView(v)
    }
}