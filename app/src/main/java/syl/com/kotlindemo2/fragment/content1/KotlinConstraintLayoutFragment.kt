package syl.com.kotlindemo2.fragment.content1

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.fragment_kotlin_constrain_layout.*
import kotlinx.android.synthetic.main.fragment_kotlin_constrain_layout.view.*
import org.jetbrains.anko.above
import org.jetbrains.anko.leftOf
import org.jetbrains.anko.sameLeft
import org.jetbrains.anko.sameTop
import org.jetbrains.anko.support.v4.dip
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment

/**
 * Created by Bright on 2019/4/13.
 * @Describe ConstraintLayout
 * @Called
 */
class KotlinConstraintLayoutFragment : BaseFragment() {
    private var isMoved = false
    private var lastViewId: Int = 0
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_constrain_layout, container, false).apply {
            lastViewId = cl_content.id
            btn_add_view.setOnClickListener {
                addNewView()
                val rl_params = RelativeLayout.LayoutParams(dip(50), dip(50))
                rl_params.leftOf(it)
                rl_params.sameTop(it)
                addNewView(rl_params)

                val rl_params2 = RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
                rl_params2.above(it.id)
                rl_params2.sameLeft(it.id)
                addNewView2(rl_params2)
            }
            btn_move_hard.setOnClickListener { moveView() }
            btn_move_soft.setOnClickListener {
                //使用动画展示新旧约束关系的切换过程。如果删掉这行则不展示切换动画。该方法需要API19支持
                TransitionManager.beginDelayedTransition(cl_content as ViewGroup?)
                moveView()
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    private fun addNewView() {
        val tv = TextView(context)
        tv.text = "长按删除该文本"
        val container = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.WRAP_CONTENT,
            ConstraintLayout.LayoutParams.WRAP_CONTENT
        )
        //设置控件左侧与另一个控件的左侧对齐
        //水平方向上只能使用start和end，因为left和right可能无法奏效
        container.startToStart = lastViewId
        //设置控件顶部与另一个控件的底部对齐
        container.topToBottom = lastViewId
        container.topMargin = dip(30)
        //左侧间距要使用Start，不能用Left，因为set.applyTo方法会清空Left的间距。marginStart需要API17支持
        container.marginStart = dip(10)
        tv.layoutParams = container
        tv.setOnLongClickListener { vv -> cl_content.removeView(vv); true }
        lastViewId += 1000
        tv.id = lastViewId
        cl_content.addView(tv)
    }

    private fun addNewView(rl_params: RelativeLayout.LayoutParams) {
        var v = View(context)
        v.setBackgroundColor(Color.GREEN)
        v.layoutParams = rl_params
        v.setOnLongClickListener {
            cl_content.removeView(it)
            true
        }
        cl_content.addView(v)
    }
    private fun addNewView2(rl_params: RelativeLayout.LayoutParams) {
        var v = TextView(context)
        v.setBackgroundColor(Color.GREEN)
        v.text="新加的TextView"
        v.layoutParams = rl_params
        v.setOnLongClickListener {
            cl_content.removeView(it)
            true
        }
        cl_content.addView(v)
    }

    private fun moveView() {
        val margin = dip((if (isMoved) 200 else 20).toFloat())
        //需要下载最新的constraint-layout，才能使用ConstraintSet
        val set = ConstraintSet()
        //复制原有的约束关系
        set.clone(cl_content)
        //清空该控件的约束关系
        //set.clear(tv_first.getId());
        //设置该控件的约束宽度
        //set.constrainWidth(tv_first.getId(), ConstraintLayout.LayoutParams.WRAP_CONTENT);
        //设置该控件的约束高度
        //set.constrainHeight(tv_first.getId(),ConstraintLayout.LayoutParams.WRAP_CONTENT);
        //设置该控件的顶部约束关系与间距
        //set.connect(tv_first.getId(), ConstraintSet.TOP, cl_content.getId(), ConstraintSet.BOTTOM, margin);
        //设置该控件的底部约束关系与间距
        //set.connect(tv_first.getId(), ConstraintSet.BOTTOM, cl_content.getId(), ConstraintSet.BOTTOM, margin);
        //设置该控件的左侧约束关系与间距
        set.connect(tv_first.id, ConstraintSet.START, cl_content.id, ConstraintSet.START, margin)
        //设置该控件的右侧约束关系与间距
        //set.connect(tv_first.getId(), ConstraintSet.END, cl_content.getId(), ConstraintSet.END, margin);
        //LEFT和RIGHT的margin不管用，只有START和END的margin才管用
        //set.setMargin(tv_init.getId(), ConstraintSet.START, 200);
        //启用新的约束关系
        set.applyTo(cl_content)
        isMoved = !isMoved
    }
}