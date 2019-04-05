package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_kotlin_param.view.*
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment

/**
 * Created by Bright on 2019/4/5.
 * @Describe 函数-可变输入参数
 * kotlin函数多变的参数列表
 * @Called
 */
class KotlinParamFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_param, container, false).apply {
            var isOdd = true //如果从初始赋值中能够知道变量类型，就无需显式指定该变量的类型
            btn_input_manual.setOnClickListener {
                tv_four_answer.text = if (isOdd) getFourBig("古代的四大发明", "造纸术", "印刷术", "火药", "指南针")
                else getFourBig("现代的四大发明", "高铁", "网购", "移动支付", "共享单车")
                isOdd = !isOdd
            }
            btn_input_default.setOnClickListener { tv_four_answer.text = getFourBigDefault("古代的四大发明") }
            btn_input_part.setOnClickListener { tv_four_answer.text = getFourBigDefault("古代的四大发明", "蔡伦发明的造纸术") }
            btn_input_name.setOnClickListener { tv_four_answer.text = getFourBigDefault("古代的四大发明", second = "活字印刷") }
            btn_param_vararg.setOnClickListener {
                //可变参数输入了三个字符串，即"丝绸","瓷器","茶叶"
                tv_four_answer.text = if (isOdd) getFourBigVararg("古代的四大发明")
                else getFourBigVararg("古代的七大发明", "造纸术", "印刷术", "火药", "指南针", "丝绸", "瓷器", "茶叶")
                isOdd = !isOdd
            }
            btn_param_array.setOnClickListener {
                //可变参数输入了两个数组变量，每个数组都使用arrayOf定义
                tv_four_answer.text = if (isOdd) getFourBigArray("古代的四大发明")
                else getFourBigArray(
                    "古代的N大发明",
                    "造纸术",
                    "印刷术",
                    "火药",
                    "指南针",
                    arrayOf("丝绸", "瓷器", "茶叶"),
                    arrayOf("国画", "中医", "武术")
                )
                isOdd = !isOdd
            }
        }
    }

    fun getFourBig(general: String, first: String, second: String, third: String, fourth: String): String {
        var answer: String = "$general：$first，$second，$third，$fourth"
        return answer
    }

    fun getFourBigDefault(
        general: String,
        first: String = "造纸术",
        second: String = "印刷术",
        third: String = "火药",
        fourth: String = "指南针"
    ): String {
        var answer: String = "$general：$first，$second，$third，$fourth"
        return answer
    }

    fun getFourBigVararg(
        general: String,
        first: String = "造纸术",
        second: String = "印刷术",
        third: String = "火药",
        fourth: String = "指南针",
        vararg otherArray: String?
    ): String {
        var answer: String = "$general：$first，$second，$third，$fourth"
        //循环取出可变参数包含的所有字段
        for (item in otherArray) {
            answer = "$answer，$item"
        }
        return answer
    }

    fun getFourBigArray(
        general: String,
        first: String = "造纸术",
        second: String = "印刷术",
        third: String = "火药",
        fourth: String = "指南针",
        vararg otherArray: Array<String>
    ): String {
        var answer: String = "$general：$first，$second，$third，$fourth"
        //先遍历每个数组
        for (array in otherArray) {
            //再遍历某个数组中的所有元素
            for (item in array) {
                answer = "$answer，$item"
            }
        }
        return answer
    }
}