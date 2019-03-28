package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_kotlin_null.view.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment

/**
 * Created by Bright on 2019/3/28.
 * @Describe 空值的判断和处理
 *
 * 总结一下，Kotlin引入了空安全的概念，并在编译时开展对象是否为空的校验。相关的操作符说明概括如下：
1、声明对象实例时，在类型名称后面加问号，表示该对象可以为空；
2、调用对象方法时，在实例名称后面加问号，表示一旦实例为空就返回null；
3、新引入运算符“?:”，一旦实例为空就返回该运算符右边的表达式；
4、新引入运算符“!!”，通知编译器不做非空校验，运行时一旦发现实例为空就扔出异常；

isNullOrEmpty : 为空指针或者字串长度为0时返回true，非空串与可空串均可调用。
isNullOrBlank : 为空指针或者字串长度为0或者全为空格时返回true，非空串与可空串均可调用。
isEmpty : 字串长度为0时返回true，只有非空串可调用。
isBlank : 字串长度为0或者全为空格时返回true，只有非空串可调用。
isNotEmpty : 字串长度大于0时返回true，只有非空串可调用。
isNotBlank : 字串长度大于0且不是全空格串时返回true，只有非空串可调用。
---------------------
作者：湖前琴亭
来源：CSDN
原文：https://blog.csdn.net/aqi00/article/details/75453959
版权声明：本文为博主原创文章，转载请附上博文链接！
 * @Called
 */
class KotlinNullFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_null, container, false).apply {
            var strA = "非空"
            var strB: String? = null
            var strC: String? = "空串"
            tv_check_title.text = "A为$strA，B为$strB，C为$strC"
            //Java在Android中的字符串空值校验函数为TextUtils.isEmpty(str)
            //strA.isNullOrEmpty() //非空串与可空串均可调用
            //strA.isNullOrBlank() //非空串与可空串均可调用
            //strA.isEmpty() //只有非空串可调用
            //strA.isBlank() //只有非空串可调用
            //strA.isNotEmpty() //只有非空串可调用
            //strA.isNotBlank() //只有非空串可调用
            btn_judge_a.setOnClickListener { tv_check_result.text = "字符串A的空值校验结果是${strA.isBlank()}" }
            btn_judge_b.setOnClickListener { tv_check_result.text = "字符串B的空值校验结果是${strB.isNullOrBlank()}" }
            btn_judge_c.setOnClickListener { tv_check_result.text = "字符串C的空值校验结果是${strC.isNullOrBlank()}" }

            var length: Int
            btn_length_a.setOnClickListener {
                tv_check_result.text = "字符串A的长度为${strA.length}"
            }
            btn_length_b.setOnClickListener {
                length = if (strB == null) -1 else strB.length
                tv_check_result.text = "字符串B的长度为$length"
            }
            btn_length_c.setOnClickListener {
                length = if (strC != null) strC.length else -1
                tv_check_result.text = "字符串C的长度为$length"
            }

            var length_null: Int?
            btn_question_dot.setOnClickListener {
                //?.表示对象为空时直接返回null，所以返回值的变量必须被声明为可空类型
                length_null = strB?.length
                tv_check_result.text = "使用?.得到字符串B的长度为$length_null"
            }
            btn_question_colon.setOnClickListener {
                //?:表示为空时就返回右边的值，即(x!=null)?x.**:y
                length = strB?.length ?: -1
                tv_check_result.text = "使用?:得到字符串B的长度为$length"
            }
            btn_exclamation_two.setOnClickListener {
                //!!表示不做非空判断，强制执行后面的表达式，如果对象为空就会扔出空异常
                //所以只有在确保为非空时，才能使用!!
                try {
                    //即使返回给可空变量length_null，也会扔出异常
                    length = strB!!.length
                    tv_check_result.text = "使用!!得到字符串B的长度为$length"
                } catch (e: Exception) {
                    tv_check_result.text = "发现空指针异常"
                }
            }
        }
    }
}