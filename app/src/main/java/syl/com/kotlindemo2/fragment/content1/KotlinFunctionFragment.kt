package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_kotlin_function.*
import kotlinx.android.synthetic.main.fragment_kotlin_function.view.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment

/**
 * Created by Bright on 2019/4/5.
 * @Describe kotlin函数的基本使用
 * Java和kotlin两相对比，可以看到二者主要有以下几点区别：
1、Java使用“@Override”表示该函数是重载了父类的方法，而Kotlin使用小写的“override”在同一行表达重载操作；
2、Java使用“public”表示该函数是公共方法，而Kotlin默认函数就是公开的，所以省略了关键字“public”；
3、Java使用“void”表示该函数没有返回参数，而Kotlin不存在关键字“void”，若无返回参数则不用特别说明；
4、Kotlin新增了关键字“fun”，表示这里是函数定义，其格式类似于Java的关键字“class”，而Java不存在关键字“fun”；
5、Java声明输入参数的格式为“对象类型 对象名称”，而Kotlin声明入参的格式为“对象名称: 对象类型”；
6、Kotlin引入了空安全机制，如果某个对象允许为空的话，需要在对象类型后面加个问号“?”；
---------------------
作者：湖前琴亭
来源：CSDN
原文：https://blog.csdn.net/aqi00/article/details/76228079
版权声明：本文为博主原创文章，转载请附上博文链接！
 * @Called
 */
class KotlinFunctionFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_function, container, false).apply {
            tv_tips.text = "Java和kotlin两相对比，可以看到二者主要有以下几点区别：\n" +
                    "1、Java使用“@Override”表示该函数是重载了父类的方法，而Kotlin使用小写的“override”在同一行表达重载操作；\n" +
                    "2、Java使用“public”表示该函数是公共方法，而Kotlin默认函数就是公开的，所以省略了关键字“public”；\n" +
                    "3、Java使用“void”表示该函数没有返回参数，而Kotlin不存在关键字“void”，若无返回参数则不用特别说明；\n" +
                    "4、Kotlin新增了关键字“fun”，表示这里是函数定义，其格式类似于Java的关键字“class”，而Java不存在关键字“fun”；\n" +
                    "5、Java声明输入参数的格式为“对象类型 对象名称”，而Kotlin声明入参的格式为“对象名称: 对象类型”；\n" +
                    "6、Kotlin引入了空安全机制，如果某个对象允许为空的话，需要在对象类型后面加个问号“?”；"
            btn_input_empty.setOnClickListener { getDinnerEmpty() }
            btn_input_param.setOnClickListener { getDinnerInput(2, 1111.1111, "水沝淼", 10000f) }
            btn_input_null.setOnClickListener { getDinnerCanNull(2, 1111.1111, null, 10000f) }
            btn_output_empty.setOnClickListener { getDinnerUnit() }
            btn_output_param.setOnClickListener { tv_result.text = getDinnerOutput() }
            btn_full_param.setOnClickListener { tv_result.text = getDinnerFull(2, 1111.1111, "水沝淼", 10000f) }
        }
    }

    //没有输入参数，也没有输出参数
    fun getDinnerEmpty(): Unit {
        tv_process.text = "只有空盘子哟"
        tv_result.text = ""
    }

    //只有输入参数
    fun getDinnerInput(egg: Int, leek: Double, water: String, shell: Float) {
        tv_process.text = "食材包括：两个鸡蛋、一把韭菜、几瓢清水"
        tv_result.text = ""
    }

    //输入参数存在空值
    fun getDinnerCanNull(egg: Int, leek: Double, water: String?, shell: Float) {
        tv_process.text = if (water != null) "食材包括：两个鸡蛋、一把韭菜、几瓢清水" else "没有水没法做汤啦"
        tv_result.text = ""
    }

    //Unit类型表示没有返回参数，可直接省略Unit声明
    fun getDinnerUnit(): Unit {
        tv_process.text = "只有空盘子哟"
        tv_result.text = ""
    }

    //只有输出参数
    fun getDinnerOutput(): String {
        tv_process.text = "只有空盘子哟"
        var dinner: String = "巧妇难为无米之炊，汝速去买菜"
        return dinner
    }

    //同时具备输入参数和输出参数
    fun getDinnerFull(egg: Int, leek: Double, water: String?, shell: Float): String {
        tv_process.text = if (water != null) "食材包括：两个鸡蛋、一把韭菜、几瓢清水" else "没有水没法做汤啦"
        var dinner: String = "两个黄鹂鸣翠柳，\n一行白鹭上青天。\n窗含西岭千秋雪，\n门泊东吴万里船。"
        return dinner
    }
}