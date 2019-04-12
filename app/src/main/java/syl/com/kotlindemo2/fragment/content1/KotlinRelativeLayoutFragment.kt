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
 * @Called
 */
class KotlinRelativeLayoutFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_relative_layout, container, false).apply {
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