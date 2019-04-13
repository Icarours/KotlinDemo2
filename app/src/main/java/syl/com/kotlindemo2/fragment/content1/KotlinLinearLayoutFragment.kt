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
 * 线性布局LinearLayout是最常用的布局，顾名思义，它下面的子视图像是用一根线串了起来，所以其内部视图的排列是有顺序的，要么从上到下垂直排列，
 * 要么从左到右水平排列。排列顺序只能指定一维方向的视图次序，可是手机屏幕是个二维的平面，这意味着还剩另一维方向需要指定视图的对齐方式。
 * 故而线性布局主要有以下两种属性设置方法：
1. setOrientation: 设置内部视图的排列方向。LinearLayout.HORIZONTAL表示水平布局，LinearLayout.VERTICAL表示垂直布局。
2. setGravity: 设置内部视图的对齐方式。Gravity.LEFT表示靠左对齐、Gravity.RIGHT表示靠右对齐、Gravity.TOP表示靠上对齐、
Gravity.BOTTOM表示靠下对齐、Gravity.CENTER表示居中对齐。
空白距离margin和间隔距离padding是另外两个常见的视图概念，margin指的当前视图与周围视图的距离，而padding指的是当前视图与内部视图的距离。

Anko库除了提供dip方法，还提供了sp、px2dip、px2sp、dimen等像素单位的转换方法，具体的方法说明见下表。
dip    将dip单位的数值转换为以px为单位的数值
sp    将sp单位的数值转换为以px为单位的数值
px2dip    将px单位的数值转换为以dip为单位的数值
px2sp    将px单位的数值转换为以sp为单位的数值
dimen    将dip单位的数值转换为以sp为单位的数值
---------------------
作者：湖前琴亭
来源：CSDN
原文：https://blog.csdn.net/aqi00/article/details/82829481
版权声明：本文为博主原创文章，转载请附上博文链接！
 * @Called
 */
class KotlinLinearLayoutFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_linear_layout, container, false).apply {
            tv_tips.text="线性布局LinearLayout是最常用的布局，顾名思义，它下面的子视图像是用一根线串了起来，所以其内部视图的排列是有顺序的，" +
                    "要么从上到下垂直排列，要么从左到右水平排列。排列顺序只能指定一维方向的视图次序，可是手机屏幕是个二维的平面，这意味着还剩另" +
                    "一维方向需要指定视图的对齐方式。故而线性布局主要有以下两种属性设置方法：\n" +
                    "1. setOrientation: 设置内部视图的排列方向。LinearLayout.HORIZONTAL表示水平布局，LinearLayout.VERTICAL表示垂直布局。\n" +
                    "2. setGravity: 设置内部视图的对齐方式。Gravity.LEFT表示靠左对齐、Gravity.RIGHT表示靠右对齐、Gravity.TOP表示靠上对齐、" +
                    "Gravity.BOTTOM表示靠下对齐、Gravity.CENTER表示居中对齐。\n" +
                    "空白距离margin和间隔距离padding是另外两个常见的视图概念，margin指的当前视图与周围视图的距离，而padding指的是当前视图与内部视图的距离。\n" +
                    "\n" +
                    "Anko库除了提供dip方法，还提供了sp、px2dip、px2sp、dimen等像素单位的转换方法，具体的方法说明见下表。\n" +
                    "dip    将dip单位的数值转换为以px为单位的数值\n" +
                    "sp    将sp单位的数值转换为以px为单位的数值\n" +
                    "px2dip    将px单位的数值转换为以dip为单位的数值\n" +
                    "px2sp    将px单位的数值转换为以sp为单位的数值\n" +
                    "dimen    将dip单位的数值转换为以sp为单位的数值"
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