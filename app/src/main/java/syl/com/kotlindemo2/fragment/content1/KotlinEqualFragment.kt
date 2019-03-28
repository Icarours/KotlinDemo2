package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_kotlin_equal.view.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment
import java.util.*

/**
 * Created by Bright on 2019/3/28.
 * @Describe 等式判断
 * @Called
 */
class KotlinEqualFragment : BaseFragment() {
    var isEqual: Boolean = true
    var count: Int = 0
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_equal, container, false).apply {
            val helloHe = "你好"
            val helloShe = "妳好"
            btn_equal_struct.setOnClickListener {
                if (isEqual) {
                    tv_check_title.text = "比较 $helloHe 和 $helloShe 是否相等"
                    //比较两个字符串是否相等的Java写法是 helloHe.equals(helloShe)
                    tv_check_result.text = "==比较的结果是${helloHe == helloShe}"
                } else {
                    tv_check_title.text = "比较 $helloHe 和 $helloShe 是否不相等"
                    //比较两个字符串是否不等的Java写法是 !helloHe.equals(helloShe)
                    tv_check_result.text = "!=比较的结果是${helloHe != helloShe}"
                }
                isEqual = !isEqual;
            }

            val date1: Date = Date()
            val date2: Any = date1.clone() //从date1原样克隆一份到date2
            btn_equal_refer.setOnClickListener {
                when (count++ % 4) {
                    0 -> {
                        //结构比较,比较二者的值
                        tv_check_title.text = "比较 $date1 和 $date2 是否相等(结构比较)"
                        tv_check_result.text = "==比较的结果是${date1 == date2}"
                    }
                    1 -> {
                        tv_check_title.text = "比较 $date1 和 $date2 是否不相等(结构比较)"
                        tv_check_result.text = "==比较的结果是${date1 != date2}"
                    }
                    2 -> {
                        //引用比较,比较的是二者的存储地址
                        tv_check_title.text = "比较 $date1 和 $date2 是否相等(引用比较)"
                        tv_check_result.text = "==比较的结果是${date1 === date2}"
                    }
                    3 -> {
                        tv_check_title.text = "比较 $date1 和 $date2 是否不相等(引用比较)"
                        tv_check_result.text = "==比较的结果是${date1 !== date2}"
                    }
                }
            }

            val oneLong = 1L
            btn_equal_type.setOnClickListener {
                if (isEqual) {
                    tv_check_title.text = "$oneLong 是Long类型"
                    //is用于判断是否等于某类型，对应的Java关键字是instanof
                    tv_check_result.text = "is 的比较结果是${oneLong is Long}"
                } else {
                    tv_check_title.text = "$oneLong 不是Long类型"
                    //!is用于判断是否不等于某类型
                    tv_check_result.text = "is 的比较结果是${oneLong !is Long}"
                }
                isEqual = !isEqual
            }

            val oneArray: IntArray = intArrayOf(1, 2, 3, 4, 5)
            val four = 4
            val nine = 9
            btn_equal_item.setOnClickListener {
                when (count++ % 4) {
                    0 -> {
                        //in用于判断变量是否位于数组或容器中，Java判断数组中是否存在某元素只能采取循环遍历的方式
                        tv_check_title.text = "$four 在数组oneArray中"
                        tv_check_result.text = "in 的比较结果是${four in oneArray}"
                    }
                    1 -> {
                        //!in用于判断变量是否不在数组或容器中
                        tv_check_title.text = "$four 不在数组oneArray中"
                        tv_check_result.text = "in 的比较结果是${four !in oneArray}"
                    }
                    2 -> {
                        //in用于判断变量是否位于数组或容器中，Java判断数组中是否存在某元素只能采取循环遍历的方式
                        tv_check_title.text = "$nine 在数组oneArray中"
                        tv_check_result.text = "in 的比较结果是${nine in oneArray}"
                    }
                    3 -> {
                        //!in用于判断变量是否不在数组或容器中
                        tv_check_title.text = "$nine 不在数组oneArray中"
                        tv_check_result.text = "in 的比较结果是${nine !in oneArray}"
                    }
                }
            }
        }
    }
}