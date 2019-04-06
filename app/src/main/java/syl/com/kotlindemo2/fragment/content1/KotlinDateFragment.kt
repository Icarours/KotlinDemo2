package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_kotlin_date.*
import kotlinx.android.synthetic.main.fragment_kotlin_date.view.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment
import syl.com.kotlindemo2.util.*
import java.util.*

/**
 * Created by Bright on 2019/4/6.
 * @Describe 日期函数
 * 小写的yyyy：表示四位年份数字，如1949、2017等等。
大写的MM：表示两位月份数字，如01表示一月份，12表示12月份。
小写的dd：表示两位日期数字，如08表示当月八号，26表示当月二十六号。
大写的HH：表示24小时制的两位小时数字，如19表示晚上七点。
小写的hh：表示12小时制的两位小时数字，如06可同时表示早上六点与傍晚六点；因为12小时制的表达会引发歧义，所以实际开发中很少这么使用。
小写的mm：表示两位分钟数字，如30表示某点三十分。
小写的ss：表示两位秒钟数字。
大写的SSS：表示三位毫秒数字。
其余的横线“-”、空格“ ”、冒号“:”、点号“.”等字符，仅仅是连接符，方便观看各种单位的时间数字而已；对于中文世界来说，也可采用形如“yyyy年MM月dd日HH时mm分ss秒”的格式。现在使用Kotlin的扩展函数，无需声明专门的DateUtil工具类，直接写几个系统日期Date类的扩展函数，即可实现日期时间格式转换的功能
---------------------
作者：湖前琴亭
来源：CSDN
原文：https://blog.csdn.net/aqi00/article/details/82794535
版权声明：本文为博主原创文章，转载请附上博文链接！
 * @Called
 */
class KotlinDateFragment:BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_date,container,false).apply {
            tv_tips.text="小写的yyyy：表示四位年份数字，如1949、2017等等。\n" +
                    "大写的MM：表示两位月份数字，如01表示一月份，12表示12月份。\n" +
                    "小写的dd：表示两位日期数字，如08表示当月八号，26表示当月二十六号。\n" +
                    "大写的HH：表示24小时制的两位小时数字，如19表示晚上七点。\n" +
                    "小写的hh：表示12小时制的两位小时数字，如06可同时表示早上六点与傍晚六点；因为12小时制的表达会引发歧义，所以实际开发中很少这么使用。\n" +
                    "小写的mm：表示两位分钟数字，如30表示某点三十分。\n" +
                    "小写的ss：表示两位秒钟数字。\n" +
                    "大写的SSS：表示三位毫秒数字。\n" +
                    "其余的横线“-”、空格“ ”、冒号“:”、点号“.”等字符，仅仅是连接符，方便观看各种单位的时间数字而已；对于中文世界来说，" +
                    "也可采用形如“yyyy年MM月dd日HH时mm分ss秒”的格式。现在使用Kotlin的扩展函数，无需声明专门的DateUtil工具类，" +
                    "直接写几个系统日期Date类的扩展函数，即可实现日期时间格式转换的功能\n"

            val array:Array<Double> = arrayOf(1.0, 2.0, 3.0, 4.0, 5.0)
            btn_function_extend.setOnClickListener {
                //把下标为0和3的两个数组元素进行交换
                //array可以是整型数组，也可以是双精度数组
                array.swap(0, 3)
                setArrayStr<Double>(array)
            }

            var count = 0
            var string_array:Array<String> = arrayOf("How", "do", "you", "do", "I'm   ", "Fine")
            btn_extend_higher.setOnClickListener {
                tv_function_result.text = when (count++%3) {
                    //下面是结合高阶函数与扩展函数的调用代码
                    0 -> "字符串数组按长度比较的最大值为${string_array.maxCustomize({ a, b -> a.length > b.length })}"
                    1 -> "字符串数组的默认最大值(使用高阶函数)为${string_array.maxCustomize({ a, b -> a > b })}"
                    else -> "字符串数组按去掉空格再比较长度的最大值为${string_array.maxCustomize({ a, b -> a.trim().length > b.trim().length })}"
                }
            }

            btn_extend_date.setOnClickListener {
                //以下方法调用自ExtendDate.kt，采取了扩展函数的方式
                tv_function_result.text = "扩展函数：" + when (count++%5) {
                    0 -> "当前日期时间为${Date().getNowDateTime()}"
                    1 -> "当前日期为${Date().getNowDate()}"
                    2 -> "当前时间为${Date().getNowTime()}"
                    3 -> "当前毫秒时间为${Date().getNowTimeDetail()}"
                    else -> "当前中文日期时间为${Date().getFormatTime("yyyy年MM月dd日HH时mm分ss秒")}"
                }
            }

            btn_object_date.setOnClickListener {
                //以下方法调用自DateUtil.kt，采取了单例对象的方式
                tv_function_result.text = "单例对象：" + when (count++%5) {
                    0 -> "当前日期时间为${DateUtil.nowDateTime}"
                    1 -> "当前日期为${DateUtil.nowDate}"
                    2 -> "当前时间为${DateUtil.nowTime}"
                    3 -> "当前毫秒时间为${DateUtil.nowTimeDetail}"
                    else -> "当前中文日期时间为${DateUtil.getFormatTime("yyyy年MM月dd日HH时mm分ss秒")}"
                }
            }
        }
    }
    //只有内联函数才可以被具体化
    inline fun <reified T : Number> setArrayStr(array:Array<T>) {
        var str:String = "数组元素依次排列："
        for (item in array) {
            str = str + item.toString() + ", "
        }
        tv_function_result.text = str
    }
}