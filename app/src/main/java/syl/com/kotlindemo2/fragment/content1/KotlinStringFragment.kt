package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.jetbrains.anko.support.v4.toast
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment

/**
 * Created by Bright on 2019/3/24.
 * @Describe String字符串
 * @Called
 */
class KotlinStringFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_string, container, false).also {
            val tvOrigin = it.findViewById<TextView>(R.id.tv_origin)
            var origin = tvOrigin.text.toString()
            if (origin.contains('.')) {
                origin = origin.substring(0, origin.indexOf("."))
            }
            it.findViewById<Button>(R.id.btn_int).setOnClickListener {
                tvOrigin.text = origin.toInt().toString()
            }
            it.findViewById<Button>(R.id.btn_long).setOnClickListener {
                tvOrigin.text = origin.toLong().toString()
            }
            it.findViewById<Button>(R.id.btn_double).setOnClickListener {
                tvOrigin.text = origin.toDouble().toString()
            }
            it.findViewById<Button>(R.id.btn_float).setOnClickListener {
                tvOrigin.text = origin.toFloat().toString()
            }
            it.findViewById<Button>(R.id.btn_chararray).setOnClickListener {
                val chars = origin.toCharArray()
                val stringBuilder = StringBuilder()
                for (item in chars) {
                    stringBuilder.append(item)
                    stringBuilder.append(',')
                }
                tvOrigin.text = stringBuilder.toString()
            }
            it.findViewById<Button>(R.id.btn_replace).setOnClickListener {
                tvOrigin.text = origin.replace("1", "66")
            }
            it.findViewById<Button>(R.id.btn_split).setOnClickListener {
                val list = origin.split(".")
                val stringBuilder = StringBuilder()
                for (item in list) {
                    stringBuilder.append(item)
                    stringBuilder.append(',')
                }
                tvOrigin.text = stringBuilder.toString()
            }
            val editText = it.findViewById<EditText>(R.id.et_number)
            it.findViewById<Button>(R.id.btn_cut).setOnClickListener {
                val number = editText.text.toString().toInt()
                if (origin.length < number) {
                    toast("输入长度超过字符串长度")
                } else {
                    tvOrigin.text = origin[number].toString()
                }
            }
            it.findViewById<Button>(R.id.btn_format).setOnClickListener {
                tvOrigin.text = "字符串值为:$origin"
            }
            it.findViewById<Button>(R.id.btn_length).setOnClickListener {
                tvOrigin.text = "字符串长度为:${origin.length}"
            }
            /**
             * 如果只是对单个美元符号做转义，也可直接在符号$前面加个反斜杆，即“\$”，代码如下：
             * 不过一个反斜杆仅仅对一个字符进行转义，如果要对一个字符串做转义，也就是把某个字符串的所有字符原样输出，
             * 那么只能采用形如“${'***'}”的表达式了，该表达式用单引号把待转义的字符串包起来，好处是能够保留该字符串中的所有特殊字符。
             */
            it.findViewById<Button>(R.id.btn_dollar).setOnClickListener {
                tvOrigin.text = "美元符号为${'$'}---$origin"
            }
        }
    }
}