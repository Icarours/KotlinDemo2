package syl.com.kotlindemo2.fragment.content1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import syl.com.kotlindemo2.R
import syl.com.kotlindemo2.base.BaseFragment

/**
 * Created by Bright on 2019/3/24.
 * @Describe Kotlin数组
 * @Called
 */
class KotlinArrayFragment : BaseFragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_kotlin_array, container, false).also {
            val tvContent = it.findViewById<TextView>(R.id.tv_content)
            it.findViewById<Button>(R.id.btn_int).setOnClickListener {
                val arr = arrayOf(12, 22, 32, 54)
                arrFun(arr, tvContent)
            }
            it.findViewById<Button>(R.id.btn_int_long).setOnClickListener {
                val arr = longArrayOf(12, 22, 32, 54)
                val stringBuilder = StringBuilder()
                for (item in arr) {
                    if (item == arr[arr.size - 1]) {
                        stringBuilder.append(item)
                    } else {
                        stringBuilder.append(item)
                        stringBuilder.append(",")
                    }
                }
                tvContent.text = stringBuilder.toString()
            }
            it.findViewById<Button>(R.id.btn_float).setOnClickListener {
                val arr = floatArrayOf(12.2f, 22.3f, 32.44f, 54.65f)
                val stringBuilder = StringBuilder()
                for (item in arr) {
                    if (item == arr[arr.size - 1]) {
                        stringBuilder.append(item)
                    } else {
                        stringBuilder.append(item)
                        stringBuilder.append(",")
                    }
                }
                tvContent.text = stringBuilder.toString()
            }
            it.findViewById<Button>(R.id.btn_double).setOnClickListener {
                val arr = doubleArrayOf(12.2, 22.3, 32.44, 54.65)
                val stringBuilder = StringBuilder()
                for (item in arr) {
                    if (item == arr[arr.size - 1]) {
                        stringBuilder.append(item)
                    } else {
                        stringBuilder.append(item)
                        stringBuilder.append(",")
                    }
                }
                tvContent.text = stringBuilder.toString()
            }
            it.findViewById<Button>(R.id.btn_boolean).setOnClickListener {
                val arr = booleanArrayOf(true, false, true, true, false)
                val stringBuilder = StringBuilder()
                for (item in arr) {
                    if (item == arr[arr.size - 1]) {
                        stringBuilder.append(item)
                    } else {
                        stringBuilder.append(item)
                        stringBuilder.append(",")
                    }
                }
                tvContent.text = stringBuilder.toString()
            }
            it.findViewById<Button>(R.id.btn_char).setOnClickListener {
                val arr = charArrayOf('q', '3', 'p', '9', '7')
                val stringBuilder = StringBuilder()
                for (item in arr) {
                    if (item == arr[arr.size - 1]) {
                        stringBuilder.append(item)
                    } else {
                        stringBuilder.append(item)
                        stringBuilder.append(",")
                    }
                }
                tvContent.text = stringBuilder.toString()
            }
            it.findViewById<Button>(R.id.btn_string).setOnClickListener {
                val arr: Array<String> = arrayOf("How", "Are", "You")
                val stringBuilder = StringBuilder()
                for (item in arr) {
                    if (item == arr[arr.size - 1]) {
                        stringBuilder.append(item)
                    } else {
                        stringBuilder.append(item)
                        stringBuilder.append(",")
                    }
                }
                tvContent.text = stringBuilder.toString()
            }
        }
    }

    /**
     * 遍历数组,并显示到TextView
     */
    fun arrFun(arr: Array<Int>, tvContent: TextView) {
        val stringBuilder = StringBuilder()
        for (item in arr) {
            if (item == arr[arr.size - 1]) {
                stringBuilder.append(item)
            } else {
                stringBuilder.append(item)
                stringBuilder.append(",")
            }
        }
        tvContent.text = stringBuilder.toString()
    }
}
