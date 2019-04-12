package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.fragment_kotlin_linear_layout.view.*
import org.jetbrains.anko.dip
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment

/**
 * Created by Bright on 2019/4/12.
 * @Describe LinearLayout
 * @Called
 */
class KotlinLinearLayoutFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_linear_layout, container, false).apply {
            //设置ll_margin内部视图的排列方式为水平排列
            ll_margin.orientation = LinearLayout.HORIZONTAL
            //设置ll_margin内部视图的对齐方式为居中对齐
            ll_margin.gravity = Gravity.CENTER
            btn_margin_vertical.setOnClickListener {
                //Kotlin对变量进行类型转换的关键字是as
                val params = ll_margin.layoutParams as LinearLayout.LayoutParams
                //setMargins方法为设置该视图与外部视图的空白距离
                //此处设置左边和右边的margin空白距离为50dp
                params.setMargins(0, dip(50), 0, dip(50))
                ll_margin.layoutParams = params
            }
            btn_margin_horizontal.setOnClickListener {
                val params = ll_margin.layoutParams as LinearLayout.LayoutParams
                //此处设置顶部和底部的margin空白距离为50dp
                params.setMargins(dip(50), 0, dip(50), 0)
                ll_margin.layoutParams = params
            }
            //setPadding方法为设置该视图与内部视图的间隔距离
            btn_padding_vertical.setOnClickListener {
                //此处设置左边和右边的padding间隔距离为50dp
                ll_margin.setPadding(0, dip(50), 0, dip(50))
            }
            btn_padding_horizontal.setOnClickListener {
                //此处设置顶部和底部的padding间隔距离为50dp
                ll_margin.setPadding(dip(50), 0, dip(50), 0)
            }
        }
    }
}